package com.example.grpc.client.grpcclient.Rest;

import java.io.Serializable;

public class HttpResult<T> implements Serializable {
    private Boolean isSuccess;
    private int code;
    private T data;
    private String message;

    /*** Constructors start ***/
    private HttpResult(){
        this.code = 200;
        this.isSuccess = true;
    }

    private HttpResult(T data){
        this.code = 200;
        this.data = data;
        this.isSuccess = true;
    }

    private HttpResult(ResultCodeEnum resultCodeEnum){
        this.code = resultCodeEnum.getCode();
        this.isSuccess = false;
        this.message = resultCodeEnum.getMessage();
    }
    /*** Constructors end ***/

    // success without result
    public static<T> HttpResult<T> success(){
        return new HttpResult();
    }

    // success with result
    public static<T> HttpResult<T> success(T data){
        return new HttpResult<T>(data);
    }

    // failure
    public static<T> HttpResult<T> failure(ResultCodeEnum resultCode){
        return  new HttpResult<T>(resultCode);
    }

    public Boolean getIsSuccess(){
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess){
        this.isSuccess = isSuccess;
    }

    public int getCode(){
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return "{\n\tisSuccess=" + isSuccess +
                ",\n\tcode=" + code +
                ",\n\tdata=" + data +
                ",\n\tmessage='" + message + '\'' + "\n" +
                '}';
    }
}
