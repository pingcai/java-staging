package me.pingcai.service.impl;

import com.qiniu.storage.model.DefaultPutRet;
import lombok.extern.slf4j.Slf4j;
import me.pingcai.dao.entity.Test;
import me.pingcai.dao.mapper.TestMapper;
import me.pingcai.enums.BackCode;
import me.pingcai.exception.ServiceException;
import me.pingcai.service.AdminService;
import me.pingcai.service.QiniuService;
import me.pingcai.util.StringUtils;
import me.pingcai.vo.rsp.UeditorResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * create by 黄平财 at 2018/1/7 12:55
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Resource
    private QiniuService qiniuService;


    @Resource
    private TestMapper testMapper;

    @Override
    public int insert(Test test) {
        return testMapper.insert(test);
    }

    @Override
    public UeditorResponse uploadImage(MultipartFile file) {

        if (file == null) {
            throw new ServiceException(BackCode.MISS_REQUESTED_PARAMS.getMessage());
        }

        UeditorResponse response;
        try {
            String fileName = StringUtils.uuid(true);
            if (org.apache.commons.lang3.StringUtils.isNotBlank(file.getOriginalFilename())) {
                fileName += file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            }
            DefaultPutRet ret = qiniuService.put(file.getInputStream(), fileName, null, file.getContentType());

            response = UeditorResponse.getInstance(true);
            response.setUrl(qiniuService.genUrl(ret.key));
            response.setType(file.getContentType());
            response.setSize(String.valueOf(file.getSize()));
            response.setOriginal(file.getOriginalFilename());

        } catch (IOException e) {
            log.error("MultipartFile getInputStream Error :", e);
            response = UeditorResponse.getInstance(false);
        }
        return response;
    }
}
