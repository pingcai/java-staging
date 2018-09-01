package me.pingcai.web.handler;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.enums.HttpError;
import me.pingcai.exception.ApiException;
import me.pingcai.vo.Response;
import me.pingcai.vo.ResponseFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
        return ResponseFactory.build(HttpError.ERROR);
    }

    @ExceptionHandler(value = ApiException.class)
    public Object throwable(ApiException e) {
        return ResponseFactory.build(e.getError());
    }

    @ExceptionHandler
    public Object methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String msg = "无法将参数 " + e.getName() + " 设置为 " + e.getValue();
        return ResponseFactory.build(HttpError.INVALID_PARAM, msg);
    }

    @ExceptionHandler
    public Object bindException(BindException bindException) {
        Response resp = ResponseFactory.build(HttpError.INVALID_PARAM);
        String msg = bindException.getAllErrors().get(0).getDefaultMessage();
        resp.setMessage(msg);
        return resp;
    }
}
