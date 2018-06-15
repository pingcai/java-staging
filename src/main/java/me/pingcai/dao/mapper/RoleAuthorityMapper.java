package me.pingcai.dao.mapper;

import java.util.List;
import me.pingcai.dao.entity.RoleAuthority;

public interface RoleAuthorityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleAuthority record);

    RoleAuthority selectByPrimaryKey(Long id);

    List<RoleAuthority> selectAll();

    int updateByPrimaryKey(RoleAuthority record);
}