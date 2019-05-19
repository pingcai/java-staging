package me.pingcai.exception;

import me.pingcai.enums.ReturnCode;

/**
 * 业务异常
 * @author huangpingcai
 * @since 2019-05-19 15:08
 */
public class BizException extends RuntimeException {

    ReturnCode returnCode;

    public BizException(ReturnCode returnCode) {
        this.returnCode = returnCode;
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ReturnCode getReturnCode() {
        return returnCode;
    }
}
