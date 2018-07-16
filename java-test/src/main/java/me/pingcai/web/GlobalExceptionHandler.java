package me.pingcai.web;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.exception.CustomException;
import me.pingcai.vo.ResponseFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ExceptionHandler(value = CustomException.class)
    public Object customException(CustomException e) {
        log.error("customException : ", e);
        return ResponseFactory.buildError(e.getCode());
    }

}
