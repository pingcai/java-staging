package me.pingcai.vo;

import me.pingcai.enums.BackCode;
import me.pingcai.enums.Code;

/**
 * create by 黄平财 at 2018/1/6 00:49
 */
public final class ResponseFactory {

    public static <T> Response build(Code code){
        Response resp = Response.newInstance(code);
        return resp;
    }

    public static <T> Response build(Code code,T t){
        Response resp = Response.newInstance(code,t);
        return resp;
    }

    public static <T> Response buildSuccess(T t){
        Response resp = Response.newInstance(BackCode.SUCCESS,t);
        return resp;
    }

    public static <T> Response buildError(){
        Response resp = Response.newInstance(BackCode.ERROR);
        return resp;
    }

    public static <T> Response buildError(T t){
        Response resp = Response.newInstance(BackCode.ERROR,t);
        return resp;
    }



}
