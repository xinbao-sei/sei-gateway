package com.ecmp.apigateway.manager.service;

import com.ecmp.apigateway.manager.entity.GatewayApiService;
import com.ecmp.apigateway.manager.entity.common.SearchParam;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author: hejun
 * @date: 2018/4/25
 * @remark: 应用服务-业务层接口
 */
public interface IGatewayApiServiceService {

    /**
     * 新增应用服务
     * @param gatewayApiService 应用服务-实体参数
     */
    void save(GatewayApiService gatewayApiService);

    /**
     * 编辑应用服务
     * @param gatewayApiService 应用服务-实体参数
     */
    void edit(GatewayApiService gatewayApiService);

    /**
     * 根据ID删除应用服务
     * @param id 主键ID
     */
    void removeById(String id);

    /**
     * 根据ID修改应用服务启用|停用状态
     * @param id 主键ID
     * @param enable 是否启用：true|false
     */
    void enableById(String id, boolean enable);

    /**
     * 查询所有应用服务
     * @return
     */
    List<GatewayApiService> findAll();

    /**
     * 分页查询应用服务
     * @param searchParam 查询参数
     * @return
     */
    Page<GatewayApiService> findAllByPage(SearchParam searchParam);

    /**
     * 根据ID查询应用服务
     * @param id 主键ID
     * @return
     */
    GatewayApiService findById(String id);

    /**
     * 获取配置中心应用服务
     * @return
     */
    Object findAllApiApp();

    /**
     * 刷新路由
     *
     * @return
     */
    void refresh();

    /**
     * 通过appId获取应用服务
     *
     * @param appId
     * @return
     */
    GatewayApiService findByAppId(String appId);
}