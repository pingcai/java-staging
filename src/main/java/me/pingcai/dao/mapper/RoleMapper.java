package me.pingcai.dao.mapper;

import java.util.List;

import me.pingcai.dao.entity.Authority;
import me.pingcai.dao.entity.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    List<Authority> selectAllAuthorityByUserId(Long id);
}