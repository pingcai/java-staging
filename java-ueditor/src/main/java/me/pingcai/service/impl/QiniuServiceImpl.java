package me.pingcai.service.impl;

import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.extern.slf4j.Slf4j;
import me.pingcai.exception.ServiceException;
import me.pingcai.service.QiniuService;
import me.pingcai.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;

@Slf4j
@Service
public class QiniuServiceImpl implements QiniuService {

    public static final String DEFAULT_RETURN_BODY = "{\"key\":\"$(key)\",\"hash\":\"$(etag)\"}";

    private Configuration conf = new Configuration(Zone.autoZone());

    private UploadManager uploadManager = new UploadManager(conf);

    @Value("${qiniu.ak}")
    private String ak;

    @Value(("${qiniu.sk}"))
    private String sk;

    @Value("${qiniu.bucket.name}")
    private String bucket;

    @Value("${qiniu.token.expires}")
    private String expires;

    @Value("${qiniu.domain}")
    private String domain;

    @Override
    public DefaultPutRet put(byte[] bytes, String key) {
        return doPut(bytes, key, DEFAULT_RETURN_BODY, DefaultPutRet.class);
    }

    @Override
    public <T> T put(byte[] bytes, String key, String returnBody, Class<T> respClass) {
        return doPut(bytes, key, returnBody, respClass);
    }

    @Override
    public DefaultPutRet put(String path, String key) {
        return doPut(path, key, DEFAULT_RETURN_BODY, DefaultPutRet.class);
    }

    @Override
    public <T> T put(String path, String key, String returnBody, Class<T> respClass) {
        return doPut(path, key, returnBody, respClass);
    }

    @Override
    public DefaultPutRet put(File file, String key) {
        return doPut(file, key, DEFAULT_RETURN_BODY, DefaultPutRet.class);
    }

    @Override
    public <T> T put(File file, String key, String returnBody, Class<T> respClass) {
        return doPut(file, key, returnBody, respClass);
    }

    @Override
    public DefaultPutRet put(InputStream inputStream, String key, StringMap params, String mime) {
        return put(inputStream, key, DEFAULT_RETURN_BODY, DefaultPutRet.class, params, mime);
    }

    @Override
    public <T> T put(InputStream inputStream, String key, String returnBody, Class<T> respClass, StringMap params, String mime) {
        if (inputStream == null) {
            throw new NullPointerException();
        }

        Auth auth = Auth.create(ak, sk);
        String upToken;
        if (StringUtils.isNotBlank(returnBody)) {
            StringMap map = new StringMap();
            map.put("returnBody", returnBody);
            upToken = auth.uploadToken(bucket, key, Long.valueOf(expires), map);
        } else {
            upToken = auth.uploadToken(bucket);
        }

        try {
            Response response = uploadManager.put(inputStream, key, upToken, params, mime);
            //解析上传成功的结果
            T t = JsonUtils.json2Object(response.bodyString(), respClass);
            return t;
        } catch (Exception ex) {
            log.error("upload to qiniu error : ", ex);
            throw new ServiceException(ex);
        }
    }

    @Override
    public String genUrl(String fileName) {
        return domain + "/" + fileName;
    }

    private <T> T doPut(Object obj, String key, String returnBody, Class<T> respClass) {
        if (obj == null) {
            throw new NullPointerException();
        }

        Auth auth = Auth.create(ak, sk);
        String upToken;
        if (StringUtils.isNotBlank(returnBody)) {
            StringMap map = new StringMap();
            map.put("returnBody", returnBody);
            upToken = auth.uploadToken(bucket, key, Long.valueOf(expires), map);
        } else {
            upToken = auth.uploadToken(bucket);
        }

        try {
            Response response;
            if (obj instanceof File) {
                // file
                response = uploadManager.put((File) obj, key, upToken);
            } else if (obj instanceof String) {
                // path
                response = uploadManager.put((String) obj, key, upToken);
            } else if (obj instanceof byte[]) {
                // byte array
                response = uploadManager.put((byte[]) obj, key, upToken);
            } else if (obj instanceof File) {
                response = uploadManager.put((File) obj, key, upToken);
            } else {
                throw new UnsupportedOperationException("unsupported params type");
            }
            //解析上传成功的结果
            T t = JsonUtils.json2Object(response.bodyString(), respClass);
            return t;
        } catch (Exception ex) {
            log.error("upload to qiniu error : ", ex);
            throw new ServiceException(ex);
        }
    }

}