package com.helltractor.demo.interceptor;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogGrpcInterceptor implements ClientInterceptor {

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            final MethodDescriptor<ReqT, RespT> method,
            final CallOptions callOptions,
            final Channel next) {

        log.info("Received call to {}", method.getFullMethodName());

        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {

            @Override
            public void sendMessage(ReqT message) {
                log.debug("Request message: {}", message);
                super.sendMessage(message);
            }

            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                super.start(
                        new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(responseListener) {
                    @Override
                    public void onMessage(RespT message) {
                        log.debug("Response message: {}", message);
                        super.onMessage(message);
                    }

                    @Override
                    public void onHeaders(Metadata headers) {
                        log.debug("gRPC headers: {}", headers);
                        super.onHeaders(headers);
                    }

                    @Override
                    public void onClose(Status status, Metadata trailers) {
                        log.info("Interaction ends with status: {}", status);
                        log.info("Trailers: {}", trailers);
                        super.onClose(status, trailers);
                    }
                }, headers);
            }
        };
    }

}
