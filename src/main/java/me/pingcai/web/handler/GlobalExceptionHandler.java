package me.pingcai.web.handler;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.enums.ReturnCode;
import me.pingcai.exception.ApiException;
import me.pingcai.domain.HttpResponse;
import me.pingcai.domain.HttpResponseFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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
@SuppressWarnings({"unchecked", "rawtypes"})
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

    @ExceptionHandler
    public Object httpMessageNotReadableException(HttpMessageNotReadableException e){
        return HttpResponseFactory.build(ReturnCode.INVALID_PARAM);
    }

    @ExceptionHandler
    public Object httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        return HttpResponseFactory.build(ReturnCode.INVALID_PARAM);
    }
}
