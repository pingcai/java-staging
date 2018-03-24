package me.pingcai.service;

import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.StringMap;

import java.io.File;
import java.io.InputStream;

public interface QiniuService {

    DefaultPutRet put(byte[] bytes, String key);

    <T> T put(byte[] bytes, String key,String returnBody,Class<T> respClass);

    DefaultPutRet put(String path, String key);

    /**
     *
     * @param path
     * @param key 文件名
     * @param respClass
     * @param <T>
     * @return
     */
    <T> T put(String path, String key,String returnBody,Class<T> respClass);

    DefaultPutRet put(File file, String key);

    /**
     *
     * @param file
     * @param key 文件名
     * @param respClass
     * @param <T>
     * @return
     */
    <T> T put(File file, String key,String returnBody,Class<T> respClass);


    DefaultPutRet put(InputStream inputStream, String key, StringMap params, String mime);

    /**
     *
     * @param file
     * @param key 文件名
     * @param respClass
     * @param <T>
     * @return
     */
    <T> T put(InputStream inputStream, String key,String returnBody,Class<T> respClass, StringMap params, String mime);

    String genUrl(String fileName);
}
