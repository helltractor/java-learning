package com.helltractor.demo.service;

import com.helltractor.demo.proto.HelloAndByeReply;
import com.helltractor.demo.proto.HelloReply;
import com.helltractor.demo.proto.HelloRequest;
import com.helltractor.demo.proto.SimpleServiceGrpc;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SimpleClientService {
    
    @GrpcClient("cloud-grpc-server")
    private SimpleServiceGrpc.SimpleServiceBlockingStub simpleServiceStub;
    
    public HelloReply sayHello(final HelloRequest request) {
        return this.simpleServiceStub.sayHello(request);
    }
    
    public String sendHelloMessage(final String name) {
        try {
            final HelloReply response = this.simpleServiceStub.sayHello(HelloRequest.newBuilder().setName(name).build());
            return response.getMessage();
        } catch (final StatusRuntimeException e) {
            log.error("Request failed", e);
            return "FAILED with " + e.getStatus().getCode();
        }
    }
    
    public String sendByeMessage(final String name) {
        try {
            final HelloAndByeReply response = this.simpleServiceStub.sayHelloAndBye(HelloRequest.newBuilder().setName(name).build());
            return response.getMessage();
        } catch (final StatusRuntimeException e) {
            log.error("Request failed", e);
            return "FAILED with " + e.getStatus().getCode();
        }
    }
    
}