package com.helltractor.demo.config;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyArgumentResolverConfigurer implements SmartInitializingSingleton {
    
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    
    @Override
    public void afterSingletonsInstantiated() {
        List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> resolvers = new ArrayList<>(argumentResolvers.size() + 1);
        for (HandlerMethodArgumentResolver resolver : argumentResolvers) {
            resolvers.add(resolver);
            if (resolver instanceof RequestResponseBodyMethodProcessor) {
                resolvers.add(new TimestampRequestBodyMethodProcess(
                        (RequestResponseBodyMethodProcessor) resolver));
            }
        }
        requestMappingHandlerAdapter.setArgumentResolvers(resolvers);
    }
    
}
