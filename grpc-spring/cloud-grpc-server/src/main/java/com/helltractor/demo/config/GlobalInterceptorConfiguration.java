package com.helltractor.demo.config;

import com.helltractor.demo.interceptor.JwtGrpcClientInterceptor;
import com.helltractor.demo.interceptor.JwtGrpcServerInterceptor;
import com.helltractor.demo.interceptor.LogGrpcInterceptor;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class GlobalInterceptorConfiguration {

    @GrpcGlobalServerInterceptor
    LogGrpcInterceptor logGrpcInterceptor() {
        return new LogGrpcInterceptor();
    }

    @GrpcGlobalClientInterceptor
    JwtGrpcClientInterceptor jwtGrpcClientInterceptor() {
        return new JwtGrpcClientInterceptor();
    }

    @GrpcGlobalServerInterceptor
    JwtGrpcServerInterceptor jwtGrpcServerInterceptor() {
        return new JwtGrpcServerInterceptor();
    }

}
