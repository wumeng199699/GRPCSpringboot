package com.example.grpc.client.grpcclient.Theads;

import com.example.grpc.server.grpcserver.BlockServiceGrpc;
import com.example.grpc.server.grpcserver.Matrix;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;

public class StubManagement<stubs> {

    private List<StubInit> stubs = new ArrayList();

    public List getStubs() {
        return stubs;
    }

    public void setStubs(List stubs) {
        this.stubs = stubs;
    }

    public class GrpcAddress{
        private String hostIP;
        private int port;

        public GrpcAddress(String hostIP, int port){
            this.hostIP = hostIP;
            this.port = port;
        }
    }

    /**
     * get the address list of grpc server
     * @param initPort
     * @param numberServer
     * @return
     */
    public List <GrpcAddress> getAddressList(int initPort, int numberServer){
        String hostIP = System.getenv("CW_SERVER");
        if (hostIP == null){
            hostIP = "localhost";
        }
        List<GrpcAddress> addressList = new ArrayList<>();
        for (int i = 0; i < numberServer; i++){
            addressList.add(new GrpcAddress(hostIP, initPort++));
        }
        return addressList;
    }

    public class StubInit{
        private ManagedChannel channel;
        private BlockServiceGrpc.BlockServiceBlockingStub stub;

        StubInit(ManagedChannel channel, BlockServiceGrpc.BlockServiceBlockingStub stub){
            this.channel = channel;
            this.stub = stub;
        }

        public ManagedChannel getChannel() {
            return channel;
        }

        public void setChannel(ManagedChannel channel) {
            this.channel = channel;
        }


        public BlockServiceGrpc.BlockServiceBlockingStub getStub() {
            return stub;
        }

        public void setStub(BlockServiceGrpc.BlockServiceBlockingStub stub) {
            this.stub = stub;
        }
    }

    /**
     * initial stubs that need to be used in multiply and add tasks
     * @param addressList
     */
     public void stubList(List<GrpcAddress> addressList){
        for (GrpcAddress address:addressList){
            ManagedChannel channel = ManagedChannelBuilder.forAddress(address.hostIP, address.port)
                    .usePlaintext()
                    .build();
            BlockServiceGrpc.BlockServiceBlockingStub stub
                    = BlockServiceGrpc.newBlockingStub(channel);
            stubs.add(new StubInit(channel, stub));
        }
     }
}
