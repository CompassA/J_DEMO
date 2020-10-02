package com.study.grpc.service;

import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.api.EchoStringRequest;
import io.grpc.api.EchoStringResponse;
import io.grpc.api.EchoStringServiceGrpc;

import java.io.IOException;

/**
 * @author tomato
 * Created on 2020.10.02
 */
public class EchoStringServer {

    public static void main(String[] args) throws IOException {
        Server server = ServerBuilder.forPort(5654)
                .addService(new EchoStringServiceImpl())
                .build()
                .start();
        EchoStringServiceGrpc.EchoStringServiceBlockingStub stub =
                EchoStringServiceGrpc.newBlockingStub(
                        ManagedChannelBuilder.forAddress("127.0.0.1", 5654)
                                .usePlaintext(true)
                                .build()
                );

        EchoStringRequest request = EchoStringRequest.newBuilder()
                .setRequestStr("hello world")
                .build();
        EchoStringResponse response = stub.echoString(request);
        System.out.println(response.getResponseStr());
    }
}
