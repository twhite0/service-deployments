package com.twhite0.service.deployments.provider.controller;

import java.beans.BeanProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import com.twhite0.service.deployments.common.ServiceMetadata;
import com.twhite0.service.deployments.common.ServiceResponse;
import com.google.gson.Gson;

@RestController
@RequestMapping("/provider")
public class ProviderController {
    
    private static final Logger log = LoggerFactory.getLogger(ProviderController.class);
 
    @Autowired
    private ServiceMetadata serviceMetaData;

    @RequestMapping(method = RequestMethod.GET)
    public ServiceResponse providerService() {
        ServiceResponse sr = new ServiceResponse();
        sr.setServiceMetadata(serviceMetaData);
        log.info(new Gson().toJson(sr));
        return sr;
    }
    
}
