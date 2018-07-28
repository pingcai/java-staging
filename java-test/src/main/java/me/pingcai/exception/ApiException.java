package me.pingcai.exception;

import me.pingcai.enums.HttpError;

/**
 * @author huangpingcai
 * @since 2018/7/28 14:46
 */
public class ApiException extends RuntimeException{

    HttpError error;

    public static ApiException create(HttpError error){
        return new ApiException(error);
    }

    public ApiException(HttpError error) {
        this.error = error;
    }

    public ApiException(String message, HttpError error) {
        super(message);
        this.error = error;
    }

    public ApiException(String message, Throwable cause, HttpError error) {
        super(message, cause);
        this.error = error;
    }

    public ApiException(Throwable cause, HttpError error) {
        super(cause);
        this.error = error;
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpError error) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
    }

    public HttpError getError() {
        return error;
    }

    public void setError(HttpError error) {
        this.error = error;
    }
}
