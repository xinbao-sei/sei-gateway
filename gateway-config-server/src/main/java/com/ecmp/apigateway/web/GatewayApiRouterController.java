package com.ecmp.apigateway.web;

import com.ecmp.apigateway.model.GatewayApiRouter;
import com.ecmp.apigateway.model.PageModel;
import com.ecmp.apigateway.model.ResponseModel;
import com.ecmp.apigateway.model.SearchParam;
import com.ecmp.apigateway.service.IGatewayApiRouterService;
import com.ecmp.apigateway.zuul.event.RefreshRouteService;
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
 * @remark: 路由配置-控制层跳转
 */
@Controller
@RequestMapping(value = "/gateway_api_router")
public class GatewayApiRouterController {
    @Autowired
    private IGatewayApiRouterService gatewayApiRouterService;
    @Autowired
    private RefreshRouteService refreshRouteService;

    /**
     * 启用全部
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("startAll")
    public Object startAll() {
        refreshRouteService.refreshRoute();
        return ResponseModel.SUCCESS();
    }

    /**
     * 停用全部
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("stopAll")
    public Object stopAll() {
        gatewayApiRouterService.removeAll();
        refreshRouteService.refreshRoute();
        return ResponseModel.SUCCESS();
    }

    /**
     * 新增
     *
     * @param gatewayApiRouter
     * @return
     */
    @ResponseBody
    @RequestMapping("save")
    public Object save(GatewayApiRouter gatewayApiRouter) {
        gatewayApiRouterService.save(gatewayApiRouter);
        return ResponseModel.SUCCESS();
    }

    /**
     * 编辑
     *
     * @param gatewayApiRouter
     * @return
     */
    @ResponseBody
    @RequestMapping("edit")
    public Object edit(GatewayApiRouter gatewayApiRouter) {
        gatewayApiRouterService.edit(gatewayApiRouter);
        return ResponseModel.SUCCESS();
    }

    /**
     * 删除所有
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("removeAll")
    public Object removeAll() {
        gatewayApiRouterService.removeAll();
        return ResponseModel.SUCCESS();
    }

    /**
     * 根据ID删除
     *
     * @param id ID
     * @return
     */
    @ResponseBody
    @RequestMapping("removeById")
    public Object removeById(String id) {
        gatewayApiRouterService.removeById(id);
        return ResponseModel.SUCCESS();
    }

    /**
     * 查询所有
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("findAll")
    public Object findAll() {
        List<GatewayApiRouter> gatewayApiRouterList = gatewayApiRouterService.findAll();
        return ResponseModel.SUCCESS(gatewayApiRouterList);
    }

    /**
     * 分页查询
     *
     * @param weatherPage 是否分页：true|false
     * @param searchParam 页面查询参数
     * @return
     */
    @ResponseBody
    @RequestMapping("findAllByPage")
    public Object findAllByPage(@RequestParam(value = "weatherPage", defaultValue = "1") boolean weatherPage, SearchParam searchParam) {
        if (weatherPage) {
            Page<GatewayApiRouter> gatewayApiRouterPage = gatewayApiRouterService.findAllByPage(searchParam);
            //return ResponseModel.SUCCESS(new PageModel<>(gatewayApiRouterPage));
            return new PageModel<>(gatewayApiRouterPage);
        } else {
            List<GatewayApiRouter> gatewayApiRouterList = gatewayApiRouterService.findAll();
            //return ResponseModel.SUCCESS(gatewayApiRouterList);
            return gatewayApiRouterList;
        }
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @ResponseBody
    @RequestMapping("findById")
    public Object findById(String id) {
        GatewayApiRouter gatewayApiRouter = gatewayApiRouterService.findById(id);
        return ResponseModel.SUCCESS(gatewayApiRouter);
    }
}
