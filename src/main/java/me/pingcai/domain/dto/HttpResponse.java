package me.pingcai.domain.dto;

import lombok.Data;
import me.pingcai.enums.ReturnCode;

import java.io.Serializable;

/**
 * create by 黄平财 at 2018/1/7 00:45
 */
@Data
@SuppressWarnings({"unchecked", "rawtypes"})
public class HttpResponse<T> implements Serializable {

    private Status status;

    private int code;

    private String msg;

    private T data;

    private HttpResponse() {
    }

    private static HttpResponse build(ReturnCode returnCode) {
        HttpResponse resp = new HttpResponse();
        resp.setStatus(returnCode.getStatus());
        resp.setCode(returnCode.getCode());
        resp.setMsg(returnCode.getMsg());
        return resp;
    }

    /**
     * @param returnCode 错误码
     * @return 返回失败，使用默认的错误消息
     */
    public static HttpResponse buildError(ReturnCode returnCode) {
        return build(returnCode);
    }

    /**
     * @param returnCode 错误码
     * @return 返回失败，重写错误消息
     */
    public static HttpResponse buildError(ReturnCode returnCode, String msg) {
        HttpResponse resp = buildError(returnCode);
        resp.setMsg(msg);
        return resp;
    }

    /**
     * @return 返回成功，无数据
     */
    public static HttpResponse buildSuccess() {
        return build(ReturnCode.SUCCESS);
    }

    /**
     * @param t 具体数据
     * @return 返回成功，有数据
     */
    public static <T> HttpResponse buildSuccess(T t) {
        HttpResponse resp = buildSuccess();
        resp.setData(t);
        return resp;
    }

}
