package me.pingcai;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import me.pingcai.util.HttpClientUtils;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpProcessorBuilder;
import org.apache.http.protocol.ResponseDate;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * create by 黄平财 at 2017/12/30 13:34
 */
@Slf4j
public class HttpclientTests {
    @Test
    public void test() {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");

        response.setHeader(new BasicHeader("Set-Cookie", "c2=b; path=\"/\", c3=c; domain=\"localhost\""));
        response.addHeader(new BasicHeader("Set-Cookie", "name=tom; path=\"/\""));

        response.setHeader("content-type", "application/json");

        HeaderElementIterator iterator = new BasicHeaderElementIterator(response.headerIterator("Set-Cookie"));

        while (iterator.hasNext()) {
            HeaderElement element = iterator.nextElement();
            log.info(element.getName() + "=" + element.getValue());
            for (NameValuePair pair : element.getParameters()) {
                log.info("\t" + pair.getName() + "=" + pair.getValue());
            }
        }
    }

    @Test
    public void testEntity() throws IOException, HttpException {

        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, null);

        String content = "alb=ee";

        StringEntity str = new StringEntity(content, StandardCharsets.UTF_8.name());

        response.setEntity(str);

        response.setHeader("content-type", ContentType.APPLICATION_JSON.getMimeType());

        HttpProcessor httpProcessor = HttpProcessorBuilder.create()
                                              .add(new HttpRequestInterceptor() {
                                                  @Override
                                                  public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {

                                                  }
                                              }).add(new HttpResponseInterceptor() {
                    @Override
                    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {

                    }
                }).add(new ResponseDate())
                                              .build();

        HttpCoreContext context = HttpCoreContext.create();

        httpProcessor.process(response, context);


        System.out.println(response.toString());
    }

    @Test
    public void testCon() throws IOException {

        Socket socket = new Socket(InetAddress.getLocalHost(), 8080);

        DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(8 * 1024);
        conn.bind(socket);
        System.out.println(conn.isOpen());
        HttpConnectionMetrics metrics = conn.getMetrics();
        System.out.println(metrics.getRequestCount());
        System.out.println(metrics.getResponseCount());
        System.out.println(metrics.getReceivedBytesCount());
        System.out.println(metrics.getSentBytesCount());

        new DefaultHttpResponseFactory().newHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, new HttpCoreContext());
    }


    @Test
    public void testUtil() throws IOException {

        String url = "http://localhost:7878/al3ive";

        Map<String, Object> data = Maps.newHashMap();

        data.put("name", "jack");
        data.put("desc", "中国人");
        data.put("age", 12);

        String content = HttpClientUtils.get(url, null, data, 5000);

        System.out.println(content);


    }


    @Test
    public void testPost() throws IOException {


        String url = "http://localhost:7878/alive";

        Map<String, Object> data = Maps.newHashMap();

        data.put("name", "jack");
        data.put("desc", "中国人");
        data.put("age", 12);

        String content = HttpClientUtils.post(url, null, data, false);

        System.out.println(content);

    }

    @Test
    public void testJson() throws IOException {

        String url = "http://localhost:7878/json";

        Map<String, Object> data = Maps.newHashMap();

        data.put("name", "jack");
        data.put("desc", "中国人");
        data.put("age", 12);


        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(data);


        System.out.println(HttpClientUtils.postJson(url, json));

    }

}
