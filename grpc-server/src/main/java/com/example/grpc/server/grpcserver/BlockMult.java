package com.example.grpc.server.grpcserver;

class BlockMult {

    static int[][] addBlock(int[][] A, int[][] B)
    {
        System.out.println();
        int C[][]= new int[A.length][A[0].length];
        for (int i=0;i<C.length;i++)
        {
            for (int j=0;j<C.length;j++)
            {
                C[i][j]=A[i][j]+B[i][j];
            }
        }
        return C;
    }
    static int[][] multiplyMatrixBlock(int[][] a, int[][] b){
        int arows = a.length;
        int bcols = b[0].length;

        int[][] c = new int[arows][bcols];

        for (int i = 0; i < arows; i++){
            for (int j = 0; j < bcols; j++){
                int result = 0;
                for (int k = 0; k < b.length; k++){
                    result += a[i][k]*b[k][j];
                }
                c[i][j] = result;
            }
        }
        return c;
    }
} 