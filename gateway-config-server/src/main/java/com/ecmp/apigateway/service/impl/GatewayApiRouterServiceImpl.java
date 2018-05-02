package com.ecmp.apigateway.service.impl;

import com.ecmp.apigateway.dao.GatewayApiRouterDao;
import com.ecmp.apigateway.exception.ObjectNotFoundException;
import com.ecmp.apigateway.exception.RequestParamNullException;
import com.ecmp.apigateway.model.GatewayApiRouter;
import com.ecmp.apigateway.service.IGatewayApiRouterService;
import com.ecmp.apigateway.utils.EntityUtils;
import com.ecmp.apigateway.utils.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: hejun
 * @date: 2018/4/25
 * @remark: 路由配置-业务层实现
 */
@Service
@Transactional
public class GatewayApiRouterServiceImpl implements IGatewayApiRouterService {
    @Autowired
    private GatewayApiRouterDao gatewayApiRouterDao;

    @Override
    public void save(GatewayApiRouter gatewayApiRouter) {
        if (ToolUtils.isEmpty(gatewayApiRouter.getRouteKey()) || ToolUtils.isEmpty(gatewayApiRouter.getServiceId()) || ToolUtils.isEmpty(gatewayApiRouter.getUrl())) {
            throw new RequestParamNullException();
        } else {
            gatewayApiRouterDao.save(gatewayApiRouter);
        }
    }

    @Override
    public void edit(GatewayApiRouter gatewayApiRouter) {
        if (ToolUtils.isEmpty(gatewayApiRouter.getId()) || ToolUtils.isEmpty(gatewayApiRouter.getRouteKey()) || ToolUtils.isEmpty(gatewayApiRouter.getServiceId()) || ToolUtils.isEmpty(gatewayApiRouter.getUrl())) {
            throw new RequestParamNullException();
        } else {
            GatewayApiRouter apiRouterOnly = gatewayApiRouterDao.findByDeletedFalseAndId(gatewayApiRouter.getId());
            if (ToolUtils.isEmpty(apiRouterOnly)) {
                throw new ObjectNotFoundException();
            } else {
                EntityUtils.resolveAllFieldsSet(gatewayApiRouter, apiRouterOnly);
                gatewayApiRouterDao.save(gatewayApiRouter);
            }
        }
    }

    @Override
    public void removeByServiceId(String serviceId) {
        List<GatewayApiRouter> gatewayApiRouters = gatewayApiRouterDao.findByDeletedFalseAndServiceId(serviceId);
        if (ToolUtils.isEmpty(gatewayApiRouters)) {
            //throw new ObjectNotFoundException();
        } else {
            gatewayApiRouters.forEach(gatewayApiRouter -> gatewayApiRouter.setDeleted(true));
            gatewayApiRouters.forEach(gatewayApiRouter -> gatewayApiRouter.setEnabled(false));
            gatewayApiRouterDao.save(gatewayApiRouters);
        }
    }

    @Override
    public void enableByServiceId(String serviceId) {
        List<GatewayApiRouter> gatewayApiRouters = gatewayApiRouterDao.findByDeletedFalseAndServiceId(serviceId);
        if (ToolUtils.isEmpty(gatewayApiRouters)) {
            throw new ObjectNotFoundException();
        } else {
            gatewayApiRouters.forEach(gatewayApiRouter -> gatewayApiRouter.setEnabled(true));
            gatewayApiRouterDao.save(gatewayApiRouters);
        }
    }

    @Override
    public List<GatewayApiRouter> findByServiceId(String serviceId) {
        return gatewayApiRouterDao.findByDeletedFalseAndServiceId(serviceId);
    }
}
