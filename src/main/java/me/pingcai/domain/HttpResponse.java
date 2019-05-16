package me.pingcai.domain;

import lombok.Data;
import me.pingcai.enums.ReturnCode;

import java.io.Serializable;

/**
 * create by 黄平财 at 2018/1/7 00:45
 */
@Data
@SuppressWarnings({"unchecked", "rawtypes"})
public class HttpResponse<T> implements Serializable{

    private Status status;

    private int code;

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
        response.setCode(returnCode.getCode());
        response.setMsg(returnCode.getMsg());
        return response;
    }

    public static <T> HttpResponse newInstance(ReturnCode returnCode, T t){
        HttpResponse response = newInstance(returnCode);
        response.setData(t);
        return response;
    }
}
