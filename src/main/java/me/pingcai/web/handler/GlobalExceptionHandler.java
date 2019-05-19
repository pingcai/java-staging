package me.pingcai.web.handler;

import lombok.extern.slf4j.Slf4j;
import me.pingcai.domain.dto.HttpResponse;
import me.pingcai.enums.ReturnCode;
import me.pingcai.exception.BizException;
import me.pingcai.exception.SystemException;
import me.pingcai.util.ListUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
    public Object throwable(Exception e) {
        log.error("系统内部异常, Exception: ", e);
        return HttpResponse.buildError(ReturnCode.INTERNAL_ERROR);
    }

    @ExceptionHandler(value = SystemException.class)
    public Object systemException(SystemException e) {
        log.error("系统内部异常, SystemException: ", e);
        return HttpResponse.buildError(ReturnCode.INTERNAL_ERROR);
    }

    @ExceptionHandler(value = BizException.class)
    public Object throwable(BizException e) {
        return HttpResponse.buildError(e.getReturnCode());
    }

    @ExceptionHandler
    public Object methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String msg = "无法将参数 " + e.getName() + " 设置为 " + e.getValue();
        return HttpResponse.buildError(ReturnCode.INVALID_PARAM, msg);
    }

    @ExceptionHandler
    public Object bindException(BindException bindException) {
        String msg = ListUtils.join(",", bindException.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()));
        return HttpResponse.buildError(ReturnCode.INVALID_PARAM, msg);
    }

    @ExceptionHandler
    public Object httpMessageNotReadableException(HttpMessageNotReadableException e) {
        return HttpResponse.buildError(ReturnCode.INVALID_PARAM);
    }

    @ExceptionHandler
    public Object httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return HttpResponse.buildError(ReturnCode.INVALID_PARAM);
    }

    @ExceptionHandler
    public Object methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return HttpResponse.buildError(ReturnCode.INVALID_PARAM, ListUtils.join(",", e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList())));
    }
}
