package com.example.grpc.client.grpcclient.Rest;

import com.example.grpc.client.grpcclient.Utils.CreateMatrixFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class MatrixProcess {

    GRPCClientService grpcClientService;

    @Autowired
    public MatrixProcess(GRPCClientService grpcClientService) {
        this.grpcClientService = grpcClientService;
    }

    @PostMapping(value = "/upload")
    public HttpResult upload(@RequestParam("name") String name , @RequestParam MultipartFile file) throws IOException {
        int[][] matrix = grpcClientService.readFile(name, file);
        if (matrix != null){
            return HttpResult.success(matrix);
        }
        else return HttpResult.failure(ResultCodeEnum.SIZE_ERROR);
    }

    @GetMapping("/getMatrix")
    public HttpResult getMatrix(){
        return HttpResult.success(grpcClientService.getMatrix());
    }

    @PostMapping("/splitMatrix")
    public HttpResult splitMatrix(@RequestParam(value = "blockSize", defaultValue = "2") int blockSize){
        Map res = grpcClientService.splitMatrix(blockSize);
        if (res != null){
            return HttpResult.success(res);
        }
        else return HttpResult.failure(ResultCodeEnum.SPLIT_ERROR);
    }

    @PostMapping("/scaling")
    public HttpResult footPrinting(@RequestParam("deadline") long deadline) throws InterruptedException {
        return HttpResult.success(grpcClientService.scaling(deadline));
    }

    @PostMapping("/multiplyBlock")
    public HttpResult multiplyBlock(@RequestParam(value = "numberServer", defaultValue = "1") int numberServer) throws ExecutionException, InterruptedException{
        int[][] res = grpcClientService.multiplyBlock(numberServer);
        if (res != null){
            return HttpResult.success(res);
        }
        else return HttpResult.failure(ResultCodeEnum.MULTIPLY_ERROR);
    }

    @PostMapping("/createMatrixFile")
    public HttpResult createMatrixFile(@RequestParam("path") String path, @RequestParam("n") int n) throws IOException {
        CreateMatrixFile.writeMatrixFile(path, n);
        return HttpResult.success();
    }
}
