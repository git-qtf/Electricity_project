package com.fh.common;

public class ServerResponse {

    private Integer code;

    private Object data;

    private String message;



    public static ServerResponse success(Object data){

        return new ServerResponse(ResponseEnum.SUCCESS.getCode(),data, ResponseEnum.SUCCESS.getMessage());
    }

    public static ServerResponse success(){

        return new ServerResponse(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage());
    }

    public static ServerResponse error(String message){

        return new ServerResponse(ResponseEnum.ERROR.getCode(),message);
    }

    public static ServerResponse error(){

        return new ServerResponse(ResponseEnum.ERROR.getCode(), ResponseEnum.ERROR.getMessage());
    }
    public static ServerResponse login_error(){

        return new ServerResponse(ResponseEnum.LOING_ERROR.getCode(), ResponseEnum.LOING_ERROR.getMessage());
    }

    public ServerResponse() {
    }

    public ServerResponse(Integer code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public ServerResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getdata() {
        return data;
    }

    public void setdata(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
