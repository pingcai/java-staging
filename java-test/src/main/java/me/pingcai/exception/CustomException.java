package me.pingcai.exception;

import me.pingcai.enums.BackCode;

public class CustomException extends RuntimeException{

    private BackCode code;

    public CustomException() {
    }

    public CustomException(BackCode code){
        this.code = code;
    }


    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BackCode getCode() {
        return code;
    }

    public void setCode(BackCode code) {
        this.code = code;
    }
}
