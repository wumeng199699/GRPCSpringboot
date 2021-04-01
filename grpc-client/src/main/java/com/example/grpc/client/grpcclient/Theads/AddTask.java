package com.example.grpc.client.grpcclient.Theads;

import com.example.grpc.client.grpcclient.Utils.MatrixAdapter;
import com.example.grpc.server.grpcserver.BlockRequest;
import com.example.grpc.server.grpcserver.BlockResponse;
import com.example.grpc.server.grpcserver.BlockServiceGrpc;
import com.example.grpc.server.grpcserver.Matrix;

public class AddTask implements Runnable {
    private int[][] A;
    private int[][] B;
    private BlockServiceGrpc.BlockServiceBlockingStub stub;
    private String location;
    private TasksProcess tasksProcess;

    public AddTask(String location, int[][] a, int[][] b, BlockServiceGrpc.BlockServiceBlockingStub stub, TasksProcess tasksProcess) {
        A = a;
        B = b;
        this.stub = stub;
        this.location = location;
        this.tasksProcess = tasksProcess;
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

    @Override
    public void run(){
        try {
            MatrixAdapter matrixAdapter = new MatrixAdapter();
            Matrix a = matrixAdapter.toMatrix(A);
            Matrix b= matrixAdapter.toMatrix(B);

            BlockResponse blockResponse = stub.add(BlockRequest.newBuilder()
                    .setA(a)
                    .setB(b)
                    .build());
            Matrix c = blockResponse.getC();

            // put the result after adding into the map of add Tasks;
            tasksProcess.addTaskMap.get(location).add(matrixAdapter.toArray(c));
            // check and keep run the adding tasks
            tasksProcess.checkAddAction(location);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tasksProcess.latch.countDown();
        }
    }
}
