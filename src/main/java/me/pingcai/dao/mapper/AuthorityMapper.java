package me.pingcai.dao.mapper;

import java.util.List;
import me.pingcai.dao.entity.Authority;

public interface AuthorityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Authority record);

    Authority selectByPrimaryKey(Long id);

    List<Authority> selectAll();

    int updateByPrimaryKey(Authority record);
}