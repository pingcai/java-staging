package me.pingcai.enums;

/**
 * create by 黄平财 at 2018/1/7 00:40
 */
public enum BackCode implements Code{

    ERROR(0,"系统异常"),
    SUCCESS(1,"操作成功"),
    HTTP_METHOD_NOT_SUPPORT(5,"不支持该http请求方法"),
    UPLOAD_FILE_EXCEEDED_MAX_SIZE(6,"上传文件超出大小限制"),
    UNSUPPORTED_OPERATION(8,"不支持该操作"),
    MISS_REQUESTED_PARAMS(10,"缺少必要参数");

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
