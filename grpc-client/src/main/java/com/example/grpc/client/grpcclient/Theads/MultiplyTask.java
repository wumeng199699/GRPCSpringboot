package com.example.grpc.client.grpcclient.Theads;

import com.example.grpc.client.grpcclient.Utils.MatrixAdapter;
import com.example.grpc.server.grpcserver.BlockRequest;
import com.example.grpc.server.grpcserver.BlockResponse;
import com.example.grpc.server.grpcserver.BlockServiceGrpc;
import com.example.grpc.server.grpcserver.Matrix;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class MultiplyTask implements Callable {
    private int[][] A;
    private int[][] B;
    private long footPrint;
    private CountDownLatch latch;
    private String location;
    private BlockServiceGrpc.BlockServiceBlockingStub stub;

    public MultiplyTask(int[][] A, int[][] B, BlockServiceGrpc.BlockServiceBlockingStub stub, CountDownLatch latch){
        this.A = A;
        this.B = B;
        this.stub = stub;
        this.latch = latch;
    }

    public MultiplyTask(String location, int[][] A, int[][] B, BlockServiceGrpc.BlockServiceBlockingStub stub){
        this.location = location;
        this.A = A;
        this.B = B;
        this.stub = stub;
    }

    public int[][] getA() {
        return A;
    }

    public void setA(int[][] a) {
        A = a;
    }

    public int[][] getB() {
        return B;
    }

    public void setB(int[][] b) {
        B = b;
    }

    public long getFootPrint() {
        return footPrint;
    }

    public void setFootPrint(long footPrint) {
        this.footPrint = footPrint;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch (CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public Map call(){
        Map resMap = new HashMap();
        try {
            long startTime = System.nanoTime();

            MatrixAdapter matrixAdapter = new MatrixAdapter();
            Matrix a = matrixAdapter.toMatrix(A);
            Matrix b= matrixAdapter.toMatrix(B);

            BlockResponse blockResponse = stub.mult(BlockRequest.newBuilder()
                    .setA(a)
                    .setB(b)
                    .build());
            Matrix c = blockResponse.getC();

            long endTime = System.nanoTime();
            footPrint = endTime - startTime;

            int[][] res = matrixAdapter.toArray(c);
            resMap.put(location, res);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
        return resMap;
    }
}
