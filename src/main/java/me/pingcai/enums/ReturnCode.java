package me.pingcai.enums;

import me.pingcai.vo.Status;

/**
 * create by 黄平财 at 2018/1/7 00:40
 */
public enum ReturnCode {

    SUCCESS(Status.SUCCESS, "操作成功"),
    FAIL(Status.FAIL, "系统内部异常"),

    USER_NOT_EXIST(Status.FAIL, "用户不存在"),
    INVALID_PARAM(Status.ERROR, "参数不合法"),
    USER_EXIST(Status.ERROR, "用户已存在");

    private Status status;
    private String msg;

    ReturnCode(Status status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Status getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
