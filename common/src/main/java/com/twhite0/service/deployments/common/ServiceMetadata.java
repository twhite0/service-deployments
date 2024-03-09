package com.twhite0.service.deployments.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ServiceMetadata {
    @Value("${REGION:localhost}")
    private String region;

    @Value("${CLUSTER:localhost}")
    private String cluster;

    @Value("${NAMESPACE:localhost}")
    private String namespace;

    @Value("${HOSTNAME:localhost}")
    private String host;

}
