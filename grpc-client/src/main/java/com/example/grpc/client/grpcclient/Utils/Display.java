package com.example.grpc.client.grpcclient.Utils;

public class Display {

    // print the matrix
    public static void printMatrix(int[][] m){
        int rows = m.length;
        int cols = m[0].length;
        for (int i = 0; i < rows; i++){
            for (int j=0; j < cols; j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
