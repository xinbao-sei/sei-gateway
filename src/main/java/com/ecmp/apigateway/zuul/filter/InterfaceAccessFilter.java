package com.ecmp.apigateway.zuul.filter;

import com.ecmp.apigateway.manager.entity.GatewayInterface;
import com.ecmp.apigateway.manager.entity.common.ResponseModel;
import com.ecmp.apigateway.zuul.service.InterfaceService;
import com.ecmp.util.JsonUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * usage:接口是否转发过滤器
 * <p>
 * </p>
 * User:liusonglin; Date:2018/5/18;ProjectName:api-gateway;
 */
//@Component 已弃用，无什么意义
@Deprecated
public class InterfaceAccessFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(InterfaceAccessFilter.class);

    @Autowired
    private InterfaceService interfaceService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            String uri = ctx.getRequest().getServletPath();
//            GatewayInterface interfaces = interfaceService.getInterfaceByUri(uri);
//            log.info("获取interfaces 成功，interfaces is {},uri is {}", interfaces, uri);
//            if (interfaces == null) {
                return true;
//            }
//            return !interfaces.isValid();
        }catch (Exception e){
            log.error("InterfaceAccessFilter shouldFilter exception",e);
            return false;
        }
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(ResponseModel.STATUS_NOT_FOUND);
        ctx.setResponseBody(JsonUtils.toJson(ResponseModel.NOT_FOUND("gateway.interface.deny")));
        ctx.set("isSuccess", false);
        return null;
    }
}