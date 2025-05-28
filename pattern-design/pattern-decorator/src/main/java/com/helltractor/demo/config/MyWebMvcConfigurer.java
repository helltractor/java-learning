package com.helltractor.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.List;

/**
 * This class will cause a circular dependency, instead, use MyArgumentResolverConfigurer.
 */
// @Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        for (HandlerMethodArgumentResolver resolver : argumentResolvers) {
            if (resolver instanceof RequestResponseBodyMethodProcessor) {
                resolvers.add(new TimestampRequestBodyMethodProcess(
                        (RequestResponseBodyMethodProcessor) resolver));
                return;
            }
        }
    }
    
}
