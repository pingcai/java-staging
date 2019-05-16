package me.pingcai.enums;

import me.pingcai.domain.Status;

/**
 * create by 黄平财 at 2018/1/7 00:40
 */
public enum ReturnCode {

    /**
     * 系统错误
     */
    SUCCESS(Status.SUCCESS, 0, "操作成功"),
    FAIL(Status.FAIL, 1, "系统内部异常"),
    HTTP_METHOD_NOT_ALLOW(Status.FAIL,2,  "不支持该请求方式"),


    /**
     * 业务错误
      */
    INVALID_PARAM(Status.ERROR, 1000,"参数不合法"),
    USER_NOT_EXIST(Status.FAIL, 1001, "用户不存在"),
    USER_EXIST(Status.ERROR, 10002,"用户已存在");

    private Status status;
    private int code;
    private String msg;

    ReturnCode(Status status, int code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
    }

    public Status getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
