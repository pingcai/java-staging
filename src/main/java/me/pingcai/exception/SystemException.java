package me.pingcai.exception;

/**
 * 系统异常
 * @author huangpingcai
 * @since 2019-05-19 18:01
 */
public class SystemException extends RuntimeException{
    public SystemException() {
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
