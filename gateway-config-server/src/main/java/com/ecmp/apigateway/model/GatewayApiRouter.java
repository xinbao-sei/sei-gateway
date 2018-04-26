package com.ecmp.apigateway.model;

import com.ecmp.apigateway.utils.ToolUtils;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: hejun
 * @date: 2018/4/24
 * @remark: 路由配置-实体类
 */
@Entity
@Table(name = "gateway_api_router")
public class GatewayApiRouter extends Domain {
    /**
     * 路由key
     */
    @Column(name = "key", nullable = false, length = 200)
    private String key;
    /**
     * 请求路径
     */
    @Column(name = "path", nullable = false, length = 200)
    private String path;
    /**
     * 路由指定服务id
     */
    @Column(name = "service_id")
    private String serviceId;
    /**
     * 路由指定服务url
     */
    @Column(name = "url")
    private String url;
    /**
     * 是否重试访问
     */
    @Column(name = "retryable")
    private Boolean retryAble;
    /**
     * 是否启用路由
     */
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
    /**
     * 是否过滤请求路径前缀
     */
    @Column(name = "strip_prefix")
    private boolean stripPrefix = true;
    /**
     * 路由接口名称
     */
    @Column(name = "interface_name")
    private String interfaceName;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPath() {
        if (ToolUtils.notEmpty(getKey())) {
            if (!key.startsWith("/")) {
                path = "/" + path;
            }
            if (!key.endsWith("/**") && !key.endsWith("/*")) {
                path = path + "/**";
            }
        }
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getRetryAble() {
        return retryAble;
    }

    public void setRetryAble(Boolean retryAble) {
        this.retryAble = retryAble;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isStripPrefix() {
        return stripPrefix;
    }

    public void setStripPrefix(boolean stripPrefix) {
        this.stripPrefix = stripPrefix;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
}