package me.pingcai.vo;

import me.pingcai.enums.ReturnCode;

import java.io.Serializable;

/**
 * create by 黄平财 at 2018/1/7 00:45
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class Response<T> implements Serializable{

    private int code;

    private String message;

    private T data;

    /**
     * 使用 newInstance 方法创建
     */
    private Response(){}

    public static <T> Response newInstance(){
        Response response = new Response<T>();
        return response;
    }

    public static <T> Response newInstance(ReturnCode returnCode){
        Response response = new Response<T>();
        response.setCode(returnCode.getCode());
        response.setMessage(returnCode.getMessage());
        return response;
    }

    public static <T> Response newInstance(T t){
        Response response = new Response<T>();
        response.setData(t);
        return response;
    }

    public static <T> Response newInstance(ReturnCode returnCode, T t){
        Response response = newInstance(returnCode);
        response.setData(t);
        return response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                       "code=" + code +
                       ", message='" + message + '\'' +
                       ", data=" + data +
                       '}';
    }
}
