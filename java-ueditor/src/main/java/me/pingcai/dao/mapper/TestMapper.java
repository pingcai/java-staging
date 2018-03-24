package me.pingcai.dao.mapper;

import java.util.List;
import me.pingcai.dao.entity.Test;

public interface TestMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Test record);

    Test selectByPrimaryKey(Long id);

    List<Test> selectAll();

    int updateByPrimaryKey(Test record);
}