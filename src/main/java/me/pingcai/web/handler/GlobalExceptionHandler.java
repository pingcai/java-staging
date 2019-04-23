package me.pingcai.web.handler;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.enums.ReturnCode;
import me.pingcai.exception.ApiException;
import me.pingcai.vo.HttpResponse;
import me.pingcai.vo.HttpResponseFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

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
        return HttpResponseFactory.build(ReturnCode.FAIL);
    }

    @ExceptionHandler(value = ApiException.class)
    public Object throwable(ApiException e) {
        return HttpResponseFactory.build(e.getError());
    }

    @ExceptionHandler
    public Object methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String msg = "无法将参数 " + e.getName() + " 设置为 " + e.getValue();
        return HttpResponseFactory.build(ReturnCode.INVALID_PARAM, msg);
    }

    @ExceptionHandler
    public Object bindException(BindException bindException) {
        HttpResponse resp = HttpResponseFactory.build(ReturnCode.INVALID_PARAM);
        List<String> msg = bindException.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        resp.setData(msg);
        return resp;
    }
}
