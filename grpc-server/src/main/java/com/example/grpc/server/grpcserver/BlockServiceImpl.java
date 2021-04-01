package com.example.grpc.server.grpcserver;

import io.grpc.Server;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BlockServiceImpl extends BlockServiceGrpc.BlockServiceImplBase {

    @Override
    public void mult (BlockRequest request, StreamObserver<BlockResponse> responseObserver){
        System.out.println("---------- Server started ----------");

        MatrixAdapter matrixAdapter = new MatrixAdapter();
        int[][] A = matrixAdapter.toArray(request.getA());
        int[][] B = matrixAdapter.toArray(request.getB());
        if (A !=null && B !=null){
            System.out.println("doing multiplying ......");
        }

        int[][] C = BlockMult.multiplyMatrixBlock(A, B);
        Matrix c = matrixAdapter.toMatrix(C);

        BlockResponse response;
        response = BlockResponse.newBuilder()
                .setC(c)
                .build();
        System.out.println("finish multiplying, the result of block size is " + C.length + "\n");
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void add (BlockRequest request, StreamObserver<BlockResponse> responseObserver){
        System.out.println("---------- Server started ----------");

        MatrixAdapter matrixAdapter = new MatrixAdapter();
        int[][] A = matrixAdapter.toArray(request.getA());
        int[][] B = matrixAdapter.toArray(request.getB());
        if (A !=null && B !=null){
            System.out.println("doing adding ......");
        }

        int[][] C = BlockMult.addBlock(A, B);
        Matrix c = matrixAdapter.toMatrix(C);

        BlockResponse response;
        response = BlockResponse.newBuilder()
                .setC(c)
                .build();

        System.out.println("finish adding, the result of block size is " + C.length + "\n");
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
