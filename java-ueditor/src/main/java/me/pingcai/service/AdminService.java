package me.pingcai.service;

import me.pingcai.dao.entity.Test;
import me.pingcai.vo.rsp.UeditorResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * create by 黄平财 at 2018/1/7 12:55
 */
public interface AdminService {

    int insert(Test test);

    UeditorResponse uploadImage(MultipartFile file);
}
