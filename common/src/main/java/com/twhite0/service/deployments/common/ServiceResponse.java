package com.twhite0.service.deployments.common;

import lombok.Getter;
import lombok.Setter;
import com.twhite0.service.deployments.common.ServiceMetadata;

@Getter
@Setter
public class ServiceResponse {
    private String correlationId = "empty";
    private String statusCode = "200";
    private String errorResponse = "success";
    private ServiceMetadata serviceMetadata;
}
