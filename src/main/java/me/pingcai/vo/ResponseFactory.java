package me.pingcai.vo;

import me.pingcai.enums.BackCode;
import me.pingcai.enums.Code;

/**
 * create by 黄平财 at 2018/1/6 00:49
 */
public final class ResponseFactory {

    public static <T> Response build() {
        return Response.newInstance();
    }

    public static <T> Response build(Code code) {
        return Response.newInstance(code);
    }

    public static <T> Response build(Code code, T t) {
        return Response.newInstance(code, t);
    }

    public static <T> Response buildSuccess() {
        return Response.newInstance(BackCode.SUCCESS);
    }

    public static <T> Response buildSuccess(T t) {
        return Response.newInstance(BackCode.SUCCESS, t);
    }

    public static <T> Response buildError() {
        return Response.newInstance(BackCode.ERROR);
    }

    public static <T> Response buildError(T t) {
        return Response.newInstance(BackCode.ERROR, t);
    }


}
