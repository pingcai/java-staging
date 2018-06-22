package me.pingcai.dao.mapper;

import java.util.List;
import me.pingcai.dao.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByNickName(String account);
}