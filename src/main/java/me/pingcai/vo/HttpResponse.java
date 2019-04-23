package me.pingcai.vo;

import me.pingcai.enums.ReturnCode;

import java.io.Serializable;

/**
 * create by 黄平财 at 2018/1/7 00:45
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class HttpResponse<T> implements Serializable{

    private Status status;

    private String msg;

    private T data;

    /**
     * 使用 newInstance 方法创建
     */
    private HttpResponse(){}

    public static <T> HttpResponse newInstance(){
        HttpResponse response = new HttpResponse<T>();
        return response;
    }

    public static <T> HttpResponse newInstance(ReturnCode returnCode){
        HttpResponse response = new HttpResponse<T>();
        response.setStatus(returnCode.getStatus());
        response.setMsg(returnCode.getMsg());
        return response;
    }

    public static <T> HttpResponse newInstance(ReturnCode returnCode, T t){
        HttpResponse response = newInstance(returnCode);
        response.setData(t);
        return response;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
