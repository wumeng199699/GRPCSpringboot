package com.example.grpc.server.grpcserver;

public class MatrixAdapter {
    public int[][] toArray(Matrix m){
        int rows = m.getRowCount();
        int cols = m.getRow(1).getACount();
        int[][] res = new int[rows][cols];
        for (int i = 0; i < rows; i++){
            for (int j=0; j < cols; j++){
                res[i][j] = m.getRow(i).getA(j);
            }
        }
        return res;
    }

    public Matrix toMatrix(int[][] a){
        Matrix.Builder res = Matrix.newBuilder();
        int rows = a.length;
        int cols = a[0].length;
        for (int i = 0; i < rows; i++){
            Matrix.Row.Builder row= Matrix.Row.newBuilder();
            for (int j=0; j < cols; j++){
                row.addA(a[i][j]);
            }
            res.addRow(row.build());
        }
        return res.build();
    }
}
