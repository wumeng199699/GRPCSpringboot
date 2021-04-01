package com.example.grpc.client.grpcclient.Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;

public class CreateMatrixFile {
    public static void writeMatrixFile(String path, int n) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                int randomNumber = new java.util.Random().nextInt(9)+1;
                bw.write(randomNumber + " ");
            }
            bw.newLine();
        }
        bw.close();
        System.out.println("File created successfully");
    }
}
