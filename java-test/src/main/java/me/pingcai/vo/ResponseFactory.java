package me.pingcai.vo;


import me.pingcai.enums.HttpError;

/**
 * create by 黄平财 at 2018/1/6 00:49
 */
public final class ResponseFactory {

    public static <T> Response build() {
        return Response.newInstance();
    }

    public static <T> Response build(HttpError error) {
        return Response.newInstance(error);
    }

    public static <T> Response build(HttpError error, T t) {
        return Response.newInstance(error, t);
    }

    public static <T> Response buildSuccess() {
        return Response.newInstance(HttpError.SUCCESS);
    }

    public static <T> Response buildSuccess(T t) {
        return Response.newInstance(HttpError.SUCCESS, t);
    }

}
