package me.pingcai.enums;

import me.pingcai.domain.dto.Status;

/**
 * create by 黄平财 at 2018/1/7 00:40
 */
public enum ReturnCode {

    /**
     * 系统错误
     */
    SUCCESS(Status.SUCCESS, 0, "操作成功"),
    INTERNAL_ERROR(Status.ERROR, 1, "系统内部异常, 请重试"),
    HTTP_METHOD_NOT_ALLOW(Status.FAIL,2,  "不支持该请求方式"),


    /**
     * 业务错误
      */
    INVALID_PARAM(Status.FAIL, 1000,"参数不合法"),
    USER_NOT_EXIST(Status.FAIL, 1001, "用户不存在"),
    USER_EXIST(Status.FAIL, 10002,"用户已存在"),
    NAME_PASSWORD_MISMATCH(Status.FAIL, 10003,"用户名或密码有误");

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
