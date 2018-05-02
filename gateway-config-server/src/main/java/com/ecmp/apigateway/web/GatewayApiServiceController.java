package com.ecmp.apigateway.web;

import com.ecmp.apigateway.model.GatewayApiRouter;
import com.ecmp.apigateway.model.GatewayApiService;
import com.ecmp.apigateway.model.common.PageModel;
import com.ecmp.apigateway.model.common.ResponseModel;
import com.ecmp.apigateway.model.common.SearchParam;
import com.ecmp.apigateway.service.IGatewayApiAppClient;
import com.ecmp.apigateway.service.IGatewayApiRouterService;
import com.ecmp.apigateway.service.IGatewayApiServiceService;
import com.ecmp.apigateway.utils.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author: hejun
 * @date: 2018/4/24
 * @remark: 应用服务-控制层跳转
 */
@Controller
@RequestMapping(value = "/gateway_api_service")
public class GatewayApiServiceController {
    @Autowired
    private IGatewayApiServiceService gatewayApiServiceService;
    @Autowired
    private IGatewayApiRouterService gatewayApiRouterService;
    @Autowired
    private IGatewayApiAppClient gatewayApiAppClient;

    /**
     * 显示应用服务页面
     * @return
     */
    @RequestMapping("/show")
    public String show() {
        return "main/ApplicationServiceView";
    }

    /**
     * 新增应用服务
     * @param gatewayApiService 应用服务-实体参数
     * @return
     */
    @ResponseBody
    @RequestMapping("save")
    public Object save(GatewayApiService gatewayApiService) {
        gatewayApiServiceService.save(gatewayApiService);
        return ResponseModel.SUCCESS();
    }

    /**
     * 编辑应用服务
     * @param gatewayApiService 应用服务-实体参数
     * @return
     */
    @ResponseBody
    @RequestMapping("edit")
    public Object edit(GatewayApiService gatewayApiService) {
        gatewayApiServiceService.edit(gatewayApiService);
        return ResponseModel.SUCCESS();
    }

    /**
     * 根据ID删除应用服务
     * @param id 主键ID
     * @return
     */
    @ResponseBody
    @RequestMapping("removeById")
    public Object removeById(String id) {
        gatewayApiServiceService.removeById(id);
        return ResponseModel.SUCCESS();
    }

    /**
     * 分页查询应用服务
     * @param weatherPage 是否分页：true|false
     * @param searchParam 页面查询参数
     * @return
     */
    @ResponseBody
    @RequestMapping("findAllByPage")
    public Object findAllByPage(@RequestParam(value = "weatherPage", defaultValue = "1") boolean weatherPage, SearchParam searchParam) {
        if (weatherPage) {
            Page<GatewayApiService> gatewayApiServicePage = gatewayApiServiceService.findAllByPage(searchParam);
            return new PageModel<>(gatewayApiServicePage);
        } else {
            List<GatewayApiService> gatewayApiServices = gatewayApiServiceService.findAll();
            return gatewayApiServices;
        }
    }

    /**
     * 根据ID查询应用服务
     * @param id 主键ID
     * @return
     */
    @ResponseBody
    @RequestMapping("findById")
    public Object findById(String id) {
        GatewayApiService gatewayApiService = gatewayApiServiceService.findById(id);
        return ResponseModel.SUCCESS(gatewayApiService);
    }

    /**
     * 设置应用服务网关路由
     * @param gatewayApiRouter 路由配置-实体参数
     * @return
     */
    @ResponseBody
    @RequestMapping("router/setting")
    public Object setting(GatewayApiRouter gatewayApiRouter) {
        if (ToolUtils.isEmpty(gatewayApiRouter.getId()))
            gatewayApiRouterService.save(gatewayApiRouter);
        else
            gatewayApiRouterService.edit(gatewayApiRouter);
        return ResponseModel.SUCCESS();
    }

    /**
     * 启用应用服务网关路由
     * @param id 主键ID
     * @return
     */
    @ResponseBody
    @RequestMapping("router/startById")
    public Object startById(String id) {
        gatewayApiServiceService.startById(id);
        return ResponseModel.SUCCESS();
    }

    /**
     * 停用应用服务网关路由
     * @param id 主键ID
     * @return
     */
    @ResponseBody
    @RequestMapping("router/stopById")
    public Object stopById(String id) {
        gatewayApiServiceService.stopById(id);
        return ResponseModel.SUCCESS();
    }

    /**
     * 获取配置中心应用服务
     * @return
     */
    @ResponseBody
    @RequestMapping("findAllApiApp")
    public Object findAllApiApp() {
        Object apiApplications = gatewayApiAppClient.findAllApiApp();
        return ResponseModel.SUCCESS(apiApplications);
    }

    /**
     * 根据应用ID获取应用服务信息
     * @param appId 配置中心AppId
     * @return
     */
    @ResponseBody
    @RequestMapping("findAppByAppId")
    public Object findAppByAppId(String appId) {
        Object apiApplication = gatewayApiAppClient.findAppByAppId(appId);
        return ResponseModel.SUCCESS(apiApplication);
    }
}
