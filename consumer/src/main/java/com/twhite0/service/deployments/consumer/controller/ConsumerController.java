package com.twhite0.service.deployments.consumer.controller;

import java.util.Arrays;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.google.gson.Gson;
import com.twhite0.service.deployments.common.ServiceMetadata;
import com.twhite0.service.deployments.common.ServiceResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import com.google.gson.Gson;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    
    private static final Logger log = LoggerFactory.getLogger(ConsumerController.class);
 
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServiceMetadata serviceMetaData;

    @Value("${PROVIDER_HOST}")
    private String providerHost;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<ServiceResponse []> consumerService(@RequestParam(value="providerOverride", required = false) String providerHostOverride) {
        
        //allow ?providerOverride=https://someplace.com:1234
        String provider = providerHost;
        if (providerHostOverride != null && !providerHostOverride.isEmpty()){
            provider = providerHostOverride;
        }

        //prime consumer response w/ metadata
        ServiceResponse consumerServiceResponse = new ServiceResponse();
        consumerServiceResponse.setServiceMetadata(serviceMetaData);

        //call the provider
        ServiceResponse providerServiceResponse = null;
        try {
            ResponseEntity<ServiceResponse> sr = restTemplate.exchange(provider+"/provider",
                                                                HttpMethod.GET,
                                                                null,
                                                                ServiceResponse.class);
            providerServiceResponse = sr.getBody();
   
        } catch(RestClientResponseException ex){
            providerServiceResponse = new ServiceResponse();
            providerServiceResponse.setStatusCode(ex.getStatusCode().toString());
            providerServiceResponse.setErrorResponse(ex.getStatusText() + " " + ex.getResponseBodyAsString());

        } catch(Exception e){
            providerServiceResponse = new ServiceResponse();
            providerServiceResponse.setStatusCode("500");
            providerServiceResponse.setErrorResponse(e.getLocalizedMessage());
        }

        ServiceResponse responses[] = {consumerServiceResponse, providerServiceResponse};
        log.info(new Gson().toJson(responses));
        return new ResponseEntity<ServiceResponse []>(responses, new HttpHeaders(), HttpStatus.OK);
    }
    
}
