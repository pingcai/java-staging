package me.pingcai.vo;

import me.pingcai.enums.BackCode;
import me.pingcai.enums.Code;

/**
 * create by 黄平财 at 2018/1/6 00:49
 */
public final class ResponseFactory {

    public static <T> Object buildSuccess(T t){
        Response resp = Response.newInstance(BackCode.SUCCESS,t);
        return resp;
    }

    public static <T> Object buildError(T t){
        Response resp = Response.newInstance(BackCode.ERROR,t);
        return resp;
    }

    public static <T> Object build(Code code){
        Response resp = Response.newInstance(code);
        return resp;
    }

    public static <T> Object build(Code code,T t){
        Response resp = Response.newInstance(code,t);
        return resp;
    }


}
