package me.pingcai.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 网络请求工具类
 * create by 黄平财 at 2017/12/30 16:24
 */
public class HttpClientUtils {

    private static final CloseableHttpClient CLIENT = HttpClients.createDefault();

    private static final int DEFAULT_TIMEOUT = 5000;

    private HttpClientUtils() {
    }

    public static String get(String url, Map<String, Object> headers, Map<String, Object> params, int timeout) throws IOException {
        return doGet(url, headers, params, timeout);
    }

    public static String get(String url, Map<String, Object> headers, Map<String, Object> params) throws IOException {
        return doGet(url, headers, params, DEFAULT_TIMEOUT);
    }

    public static String post(String url, Map<String, Object> params) throws IOException {
        return doPost(url, null, params, false, DEFAULT_TIMEOUT);
    }

    public static String post(String url, Map<String, Object> headers, Map<String, Object> params) throws IOException {
        return doPost(url, headers, params, false, DEFAULT_TIMEOUT);
    }

    public static String post(String url, Map<String, Object> headers, Map<String, Object> params, int timeout) throws IOException {
        return doPost(url, headers, params, false, timeout);
    }

    public static String post(String url, Map<String, Object> headers, Map<String, Object> params, boolean urlParam) throws IOException {
        return doPost(url, headers, params, urlParam, DEFAULT_TIMEOUT);
    }

    public static String postJson(String url, String json) throws IOException {
        return doPostJson(url, null, json, DEFAULT_TIMEOUT);
    }

    public static String postJson(String url, Map<String, Object> headers, String json) throws IOException {
        return doPostJson(url, headers, json, DEFAULT_TIMEOUT);
    }

    private static String doPostJson(String url, Map<String, Object> headers, String json, int timeout) throws IOException {

        HttpPost httpPost = new HttpPost(url);

        if (timeout > 0) {
            RequestConfig conf = RequestConfig.custom()
                                         .setConnectTimeout(timeout)
                                         .build();
            httpPost.setConfig(conf);
        }

        if (Objects.nonNull(headers) && headers.size() > 0) {
            headers.forEach((key, val) -> {
                httpPost.addHeader(new BasicHeader(key, String.valueOf(val)));
            });
        }

        if (Objects.isNull(httpPost.getFirstHeader(HttpHeaders.CONTENT_TYPE))) {
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        }

        StringEntity entity = new StringEntity(json, StandardCharsets.UTF_8);

        httpPost.setEntity(entity);

        return execPost(httpPost);
    }


    private static String doGet(String url, Map<String, Object> headers, Map<String, Object> params, int timeout) throws IOException {

        url = appendUrlParams(url, params);

        HttpGet httpGet = new HttpGet(url);

        if (timeout > 0) {
            RequestConfig conf = RequestConfig.custom()
                                         .setConnectTimeout(timeout)
                                         .build();
            httpGet.setConfig(conf);
        }

        if (Objects.nonNull(headers) && headers.size() > 0) {
            headers.forEach((key, val) -> {
                httpGet.addHeader(new BasicHeader(key, String.valueOf(val)));
            });
        }

        String result = CLIENT.execute(httpGet, (response -> {
            int status = response.getStatusLine().getStatusCode();
            String r = null;
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                if (Objects.nonNull(entity)) {
                    r = EntityUtils.toString(entity);
                }
            } else {
                throw new RuntimeException(String.valueOf(response.getStatusLine()));
            }
            return r;
        }));

        return result;
    }

    private static String doPost(String url, Map<String, Object> headers, Map<String, Object> params, boolean urlParam, int timeout) throws IOException {

        HttpPost httpPost = new HttpPost();

        if (timeout > 0) {
            RequestConfig conf = RequestConfig.custom()
                                         .setConnectTimeout(timeout)
                                         .build();
            httpPost.setConfig(conf);
        }

        if (urlParam) {
            url = appendUrlParams(url, headers);
        } else {
            if (Objects.nonNull(params) && !params.isEmpty()) {
                List<NameValuePair> list = new ArrayList<>();
                params.forEach((k, v) -> {
                    list.add(new BasicNameValuePair(k, String.valueOf(v)));
                });
                httpPost.setEntity(new UrlEncodedFormEntity(list, StandardCharsets.UTF_8));
            }
        }

        httpPost.setURI(URI.create(url));

        if (Objects.nonNull(headers) && headers.size() > 0) {
            headers.forEach((key, val) -> {
                httpPost.addHeader(new BasicHeader(key, String.valueOf(val)));
            });
        }

        return execPost(httpPost);
    }

    private static String execPost(HttpPost httpPost) throws IOException {
        return CLIENT.execute(httpPost, (response) -> {
            StatusLine statusLine = response.getStatusLine();
            String res = null;
            if (statusLine.getStatusCode() >= 200 && statusLine.getStatusCode() < 300) {
                HttpEntity responseEntity = response.getEntity();
                if (Objects.nonNull(responseEntity)) {
                    res = EntityUtils.toString(responseEntity);
                }
            } else {
                throw new RuntimeException(String.valueOf(response.getStatusLine()));
            }
            return res;
        });
    }

    /**
     * 请求头
     *
     * @param headersMap
     * @return
     */
    private static Header[] generateHeaders(Map<String, Object> headersMap) {
        if (Objects.nonNull(headersMap) && headersMap.size() > 0) {
            final Header[] headers = new Header[headersMap.size()];
            final int[] index = new int[1];
            headersMap.forEach((key, val) -> {
                headers[index[0]++] = new BasicHeader(key, String.valueOf(val));
            });
            return headers;
        }
        return null;
    }

    /**
     * 拼接url参数
     *
     * @param url
     * @param params
     * @return
     */
    private static String appendUrlParams(String url, Map<String, Object> params) {
        if (Objects.nonNull(params) && params.size() > 0) {
            StringBuilder sb = new StringBuilder(url).append("?");
            params.forEach((key, value) -> {
                sb.append(key).append("=").append(value).append("&");
            });
            return sb.substring(0, sb.length() - 1);
        }
        return url;
    }


}
