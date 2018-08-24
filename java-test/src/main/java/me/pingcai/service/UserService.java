package me.pingcai.service;

import me.pingcai.dao.entity.User;
import me.pingcai.vo.UserVo;

public interface UserService {

    User selectByPrimaryKey(Long id);

    Long insertIfNotExist(UserVo user);
}
