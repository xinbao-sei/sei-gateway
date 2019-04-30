package com.ecmp.apigateway.manager.service.impl;

import com.ecmp.apigateway.config.ZKService;
import com.ecmp.apigateway.manager.dao.GatewayApiServiceDao;
import com.ecmp.apigateway.exception.InvokeConfigFailException;
import com.ecmp.apigateway.exception.ObjectNotFoundException;
import com.ecmp.apigateway.exception.RequestParamNullException;
import com.ecmp.apigateway.manager.entity.GatewayApiService;
import com.ecmp.apigateway.manager.entity.common.SearchParam;
import com.ecmp.apigateway.manager.service.IGatewayApiServiceService;
import com.ecmp.apigateway.utils.EntityUtils;
import com.ecmp.apigateway.utils.ToolUtils;
import com.ecmp.apigateway.zuul.event.RefreshService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: hejun
 * @date: 2018/4/25
 * @remark: 应用服务-业务层实现
 * @update：liusonglin 去掉routerService,所有router信息放到service里，事物未提交前调用刷新不会跟新路由
 */
@Service
@Transactional
public class GatewayApiServiceServiceImpl implements IGatewayApiServiceService {

    private static final Logger logger = LoggerFactory.getLogger(GatewayApiServiceServiceImpl.class);

    @Autowired
    private GatewayApiServiceDao gatewayApiServiceDao;

    @Autowired
    private ZKService zkService;

    @Autowired
    private RefreshService refreshService;

    @Override
    public void save(GatewayApiService gatewayApiService) {
        if (ToolUtils.isEmpty(gatewayApiService.getServiceAppId()) && ToolUtils.isEmpty(gatewayApiService.getServiceAppUrl())) {
            throw new RuntimeException("应用服务ID和转发地址不能全为空");
        } else {
            if(StringUtils.isNotBlank(gatewayApiService.getServiceAppId())) {
                String appUrl = zkService.getZookeeperData(gatewayApiService.getServiceAppId(),
                        gatewayApiService.getServiceAppCode());
                logger.info("AppId:{}, AppCode:{}, AppUrl:{}",
                        gatewayApiService.getServiceAppId(), gatewayApiService.getServiceAppCode(), appUrl);
                if (StringUtils.isNotBlank(appUrl)) {
                    gatewayApiService.setServiceAppUrl(appUrl);
                    gatewayApiService.setServicePath(ToolUtils.key2Path(ToolUtils.getRouteKey(appUrl)));
                    gatewayApiServiceDao.save(gatewayApiService);
                } else {
                    throw new RuntimeException("应用服务全局参数" + gatewayApiService.getServiceAppCode() + "不能为空");
                }
            }
            if(StringUtils.isBlank(gatewayApiService.getServiceAppId())
                    &&StringUtils.isNotBlank(gatewayApiService.getServiceAppUrl())){
                gatewayApiServiceDao.save(gatewayApiService);
            }
        }
    }

    @Override
    public void edit(GatewayApiService gatewayApiService) {
        if ((ToolUtils.isEmpty(gatewayApiService.getId())
                || ((ToolUtils.isEmpty(gatewayApiService.getServiceAppId())
                || ToolUtils.isEmpty(gatewayApiService.getApplicationCode()))
                && ToolUtils.isEmpty(gatewayApiService.getServiceAppUrl())))) {
            throw new RequestParamNullException();
        } else {
            GatewayApiService apiServiceOnly = gatewayApiServiceDao.findByDeletedFalseAndId(gatewayApiService.getId());
            if (ToolUtils.isEmpty(apiServiceOnly)) {
                throw new ObjectNotFoundException();
            } else {
                EntityUtils.resolveAllFieldsSet(gatewayApiService, apiServiceOnly);
                gatewayApiServiceDao.save(gatewayApiService);
            }
        }
    }

    @Override
    public void removeById(String id) {
        List<GatewayApiService> gatewayApiServices = gatewayApiServiceDao.findByDeletedFalseAndIdIn(ToolUtils.id2List(id));
        if (ToolUtils.notEmpty(gatewayApiServices)) {
            gatewayApiServices.forEach(gatewayApiService -> {
                gatewayApiService.setDeleted(true);
                gatewayApiService.setServiceAppEnabled(false);
                gatewayApiServiceDao.save(gatewayApiService);
            });
        }
    }

    @Override
    public void enableById(String id, boolean enable) {
        List<GatewayApiService> gatewayApiServices = gatewayApiServiceDao.findByDeletedFalseAndIdIn(ToolUtils.id2List(id));
        if (ToolUtils.isEmpty(gatewayApiServices)) {
            throw new ObjectNotFoundException();
        } else {
            gatewayApiServices.forEach(gatewayApiService -> {
                gatewayApiService.setServiceAppEnabled(enable);
                if(StringUtils.isNotBlank(gatewayApiService.getServiceAppId()) && enable) {
                    //*通过应用服务AppId和应用服务Code获取
                    String appUrl = zkService.getZookeeperData(gatewayApiService.getServiceAppId(), gatewayApiService.getServiceAppCode());
                    if (ToolUtils.isEmpty(appUrl)) {
                        throw new InvokeConfigFailException();
                    } else {
                        gatewayApiService.setServiceAppUrl(appUrl);
                    }
                }
                gatewayApiServiceDao.save(gatewayApiService);
            });

        }
    }

    @Override
    public List<GatewayApiService> findAll() {
        return gatewayApiServiceDao.findByDeletedFalse();
    }

    @Override
    public Page<GatewayApiService> findAllByPage(SearchParam searchParam) {
        if (ToolUtils.isEmpty(searchParam.getKeywords())) {
            return gatewayApiServiceDao.findByDeletedFalse(searchParam.getPageable());
        }
        return gatewayApiServiceDao.findByDeletedFalseAndServiceAppNameLikeOrServiceAppRemarkLikeOrServiceAppVersionLike(searchParam.getLikeKeywords(), searchParam.getLikeKeywords(), searchParam.getLikeKeywords(), searchParam.getPageable());
    }

    @Override
    public GatewayApiService findById(String id) {
        return gatewayApiServiceDao.findByDeletedFalseAndId(id);
    }

    @Override
    public Object findAllApiApp() {
        //todo 查询所有路由
        return null;
    }

    @Override
    public void refresh() {
        refreshService.refreshRoute();
    }


    @Override
    public GatewayApiService findByAppId(String appId) {
        return gatewayApiServiceDao.findByServiceAppId(appId);
    }
}