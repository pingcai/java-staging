package me.pingcai.vo;


import me.pingcai.enums.ReturnCode;

/**
 * create by 黄平财 at 2018/1/6 00:49
 */
public final class ResponseFactory {

    public static <T> Response build() {
        return Response.newInstance();
    }

    public static <T> Response build(ReturnCode error) {
        return Response.newInstance(error);
    }

    public static <T> Response build(ReturnCode error, T t) {
        return Response.newInstance(error, t);
    }

    public static <T> Response buildSuccess() {
        return Response.newInstance(ReturnCode.SUCCESS);
    }

    public static <T> Response buildSuccess(T t) {
        return Response.newInstance(ReturnCode.SUCCESS, t);
    }

}
