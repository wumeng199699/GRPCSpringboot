package com.example.grpc.client.grpcclient.Rest;

public enum ResultCodeEnum {
    // success
    SUCCESS(200, "successful"),
    // Matrix size error
    SIZE_ERROR(500,"matrix size error"),
    // Matrix not found
    NOT_FOUND(404, "matrix not found"),
    // Matrix split error
    SPLIT_ERROR(500, "matrix split error"),
    // matrix Multiplication error
    MULTIPLY_ERROR(500, "matrix multiplication error")
    ;

    // response code
    private int code;
    //response message
    private String message;

    ResultCodeEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode(){
        return code;
    }

    public void setCode(int code){
        this.code = code;
    }

    public String getMessage(){
        return message;
    }
}
