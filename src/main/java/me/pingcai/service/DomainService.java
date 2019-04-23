package me.pingcai.service;

import me.pingcai.dao.entity.User;
import me.pingcai.vo.UserVo;

public interface DomainService {

    User selectUserByPrimaryKey(Long id);

    Long insertUserIfNotExist(UserVo user);
}
