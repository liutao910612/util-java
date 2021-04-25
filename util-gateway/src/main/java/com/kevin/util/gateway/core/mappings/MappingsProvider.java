package com.kevin.util.gateway.core.mappings;

import com.kevin.util.gateway.config.GateWayProperties;
import com.kevin.util.gateway.config.MappingProperties;
import com.kevin.util.gateway.core.http.HttpClientProvider;
import org.springframework.boot.autoconfigure.web.ServerProperties;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author:Kevin
 * @Date:Created in 17:44 2021/4/25
 */
public abstract class MappingsProvider {
    protected final ServerProperties serverProperties;
    protected final GateWayProperties gateWayProperties;
    protected final MappingsValidator mappingsValidator;
    protected final HttpClientProvider httpClientProvider;
    protected List<MappingProperties> mappings;

    public MappingsProvider(ServerProperties serverProperties, GateWayProperties gateWayProperties
            , MappingsValidator mappingsValidator, HttpClientProvider httpClientProvider) {
        this.serverProperties = serverProperties;
        this.gateWayProperties = gateWayProperties;
        this.mappingsValidator = mappingsValidator;
        this.httpClientProvider = httpClientProvider;
    }

    public MappingProperties resolveMapping(String originHost, HttpServletRequest request) {

        return null;
    }
}
