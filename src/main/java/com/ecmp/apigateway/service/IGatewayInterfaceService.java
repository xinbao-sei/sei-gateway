package com.ecmp.apigateway.service;

import com.ecmp.apigateway.model.GatewayInterface;
import com.ecmp.apigateway.model.SearchParam;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author:wangdayin
 * @date:2018/4/24
 * @remark: 网关-接口业务接口
 */
public interface IGatewayInterfaceService {
    /**
     * 新增接口信息
     *
     * @param gatewayInterface 接口实体对象
     */
    void addGatewayInterface(GatewayInterface gatewayInterface);

    /**
     * 根据id移除接口信息
     *
     * @param id ID
     */
    void removeGatewayInterface(String id);

    /**
     * 根据应用code移除接库信息
     *
     * @param applicationCode
     */
    void removeGatewayInterfaceByApplicationCode(String applicationCode);

    /**
     * 更新接口信息
     *
     * @param gatewayInterface 接口信息实体
     */
    void modifyGatewayInterface(GatewayInterface gatewayInterface);

    /**
     * 根据id查询接口信息
     *
     * @param id ID
     * @return
     */
    GatewayInterface findGatewayInterfaceById(String id);

    /**
     * 根据应用code查询接口信息
     *
     * @param applicationCode 应用code
     * @param searchParam     查询参数对象
     * @return
     */
    Page<GatewayInterface> findGatewayInterfaceByPage(String applicationCode, SearchParam searchParam);

    /**
     * 根据
     *
     * @param applicationCode
     * @return
     */
    List<GatewayInterface> findGatewayInterfaceByNoPage(String applicationCode);

    /**
     * 根据应用code，接口名称或者接口uri地址查询接口信息
     *
     * @param applicationCode 应用code
     * @param interfaceName   接口名称
     * @param interfaceURI    接口uri地址
     * @return
     */
    GatewayInterface findGatewayInterfaceByNameOrURI(String applicationCode, String interfaceName, String interfaceURI);

}
