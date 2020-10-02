package com.study.grpc.service;

import io.grpc.api.EchoStringRequest;
import io.grpc.api.EchoStringResponse;
import io.grpc.api.EchoStringServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @author tomato
 * Created on 2020.10.02
 */
public class EchoStringServiceImpl extends EchoStringServiceGrpc.EchoStringServiceImplBase {

    @Override
    public void echoString(EchoStringRequest request,
                           StreamObserver<EchoStringResponse> responseObserver) {
        EchoStringResponse response = EchoStringResponse.newBuilder()
                .setResponseStr("echo" + request.getRequestStr())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
