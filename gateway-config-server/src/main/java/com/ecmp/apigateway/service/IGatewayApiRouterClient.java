package com.ecmp.apigateway.service;

import com.ecmp.apigateway.service.impl.GatewayApiRouterClientImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: hejun
 * @date: 2018/4/26
 * @remark: 网关路由接口-业务层接口
 */
@FeignClient(name = "${gateway.route.service.name}", url = "${gateway.route.service.url}", decode404 = true, fallback = GatewayApiRouterClientImpl.class)
public interface IGatewayApiRouterClient {
    /**
     * 调用网关路由刷新
     * @return
     */
    @GetMapping(value = "${gateway.route.service.path0}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void refresh();
}