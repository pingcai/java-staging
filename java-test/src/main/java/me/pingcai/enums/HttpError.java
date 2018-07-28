package me.pingcai.enums;

/**
 * create by 黄平财 at 2018/1/7 00:40
 */
public enum HttpError{

    SUCCESS(1,"操作成功"),

    ERROR(1000,"系统异常"),
    INVALID_PARAM(10001,"参数不合法"),
    EXIST(10002,"已存在");

    private int code;
    private String message;

    HttpError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
