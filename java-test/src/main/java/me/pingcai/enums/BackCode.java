package me.pingcai.enums;

/**
 * create by 黄平财 at 2018/1/7 00:40
 */
public enum BackCode implements Code{

    ERROR(0,"系统异常"),
    SUCCESS(1,"操作成功"),
    DATETIME_FORMAT_ERROR(5,"日期格式有误");

    private int code;
    private String message;

    BackCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
