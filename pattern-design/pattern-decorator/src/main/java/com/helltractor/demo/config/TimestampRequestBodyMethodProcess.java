package com.helltractor.demo.config;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.Map;

public class TimestampRequestBodyMethodProcess implements HandlerMethodArgumentResolver {
    
    private final RequestResponseBodyMethodProcessor processor;
    
    TimestampRequestBodyMethodProcess(RequestResponseBodyMethodProcessor processor) {
        this.processor = processor;
    }
    
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(TimestampRequestBody.class);
    }
    
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        
        Object result = processor.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
        if (!(result instanceof Map<?, ?>)) {
            return result;
        }
        ((Map) result).put("timestamp", System.currentTimeMillis());
        return result;
    }
    
}
