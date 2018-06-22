package me.pingcai.dao.mapper;

import java.util.List;
import me.pingcai.dao.entity.UserRole;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    List<UserRole> selectAll();

    int updateByPrimaryKey(UserRole record);
}