package com.helltractor.demo.service;

import com.helltractor.demo.proto.HelloAndByeReply;
import com.helltractor.demo.proto.HelloReply;
import com.helltractor.demo.proto.HelloRequest;
import com.helltractor.demo.proto.SimpleServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j
public class SimpleServerService extends SimpleServiceGrpc.SimpleServiceImplBase {
    
    @GrpcClient("cloud-grpc-server")
    private SimpleServiceGrpc.SimpleServiceBlockingStub simpleServiceStub;
    
    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        try {
            HelloReply reply = HelloReply.newBuilder().setMessage("Hello ==> " + req.getName()).build();
            responseObserver.onNext(reply);
        } catch (Exception e) {
            responseObserver.onError(e);
        }
        responseObserver.onCompleted();
    }
    
    @Override
    public void sayHelloAndBye(HelloRequest req, StreamObserver<HelloAndByeReply> responseObserver) {
        try {
            HelloReply helloReply = this.simpleServiceStub.sayHello(req);
            HelloAndByeReply reply = HelloAndByeReply.newBuilder().setMessage(helloReply.getMessage() + " ==> Bye").build();
            responseObserver.onNext(reply);
        } catch (Exception e) {
            responseObserver.onError(e);
        }
        responseObserver.onCompleted();
    }
    
}