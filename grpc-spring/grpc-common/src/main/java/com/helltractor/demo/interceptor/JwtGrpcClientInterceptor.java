package com.helltractor.demo.interceptor;

import com.helltractor.demo.constant.JwtConstant;
import com.helltractor.demo.util.JwtUtil;
import io.grpc.*;
import org.springframework.beans.factory.annotation.Value;

public class JwtGrpcClientInterceptor implements ClientInterceptor {

    @Value("${spring.application.name}")
    private static String SUBJECT;

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> methodDescriptor,
            CallOptions callOptions,
            Channel next) {
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(methodDescriptor, callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                if (!headers.containsKey(JwtConstant.AUTHORIZATION_METADATA_KEY)) {
                    String token = JwtUtil.createJWT(SUBJECT);
                    headers.put(JwtConstant.AUTHORIZATION_METADATA_KEY, String.format("%s %s", JwtConstant.BEARER_TYPE, token));
                }
                super.start(responseListener, headers);
            }
        };
    }

}
