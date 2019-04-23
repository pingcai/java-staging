package me.pingcai.exception;

import me.pingcai.enums.ReturnCode;

/**
 * @author huangpingcai
 * @since 2018/7/28 14:46
 */
public class ApiException extends RuntimeException{

    ReturnCode error;

    public static ApiException newInstance(ReturnCode error){
        return new ApiException(error);
    }

    public ApiException(ReturnCode error) {
        this.error = error;
    }

    public ApiException(String message, ReturnCode error) {
        super(message);
        this.error = error;
    }

    public ApiException(String message, Throwable cause, ReturnCode error) {
        super(message, cause);
        this.error = error;
    }

    public ApiException(Throwable cause, ReturnCode error) {
        super(cause);
        this.error = error;
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ReturnCode error) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
    }

    public ReturnCode getError() {
        return error;
    }

    public void setError(ReturnCode error) {
        this.error = error;
    }
}
