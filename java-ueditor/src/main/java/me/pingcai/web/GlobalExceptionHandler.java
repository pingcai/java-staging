package me.pingcai.web;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.enums.BackCode;
import me.pingcai.vo.rsp.ResponseFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * create by 黄平财 at 2018/1/7 13:22
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Throwable.class)
    public Object throwable(Throwable throwable) {
        log.error("internal exception : ", throwable);
        return ResponseFactory.buildError();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object methodNotSupport(HttpRequestMethodNotSupportedException e) {
        log.error("http request method not support : ", e);
        return ResponseFactory.buildError(BackCode.HTTP_METHOD_NOT_SUPPORT);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public Object unsupportOper(UnsupportedOperationException e) {
        log.error("unsupport operate : ", e);
        return ResponseFactory.buildError(BackCode.UNSUPPORTED_OPERATION);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Object exceededMaxSize(MaxUploadSizeExceededException e) {
        log.error("exceeded max upload file size : ", e);
        return ResponseFactory.buildError(BackCode.UPLOAD_FILE_EXCEEDED_MAX_SIZE);
    }
}
