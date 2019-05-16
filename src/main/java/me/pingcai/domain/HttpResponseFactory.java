package me.pingcai.domain;


import me.pingcai.enums.ReturnCode;

/**
 * create by 黄平财 at 2018/1/6 00:49
 */
public final class HttpResponseFactory {

    public static <T> HttpResponse build() {
        return HttpResponse.newInstance();
    }

    public static <T> HttpResponse build(ReturnCode error) {
        return HttpResponse.newInstance(error);
    }

    public static <T> HttpResponse build(ReturnCode error, T t) {
        return HttpResponse.newInstance(error, t);
    }

    public static <T> HttpResponse buildSuccess() {
        return HttpResponse.newInstance(ReturnCode.SUCCESS);
    }

    public static <T> HttpResponse buildSuccess(T t) {
        return HttpResponse.newInstance(ReturnCode.SUCCESS, t);
    }

}
