package me.pingcai.reposiroty;

import me.pingcai.dao.entity.Test;
import me.pingcai.dao.entity.TestExample;
import me.pingcai.dao.mapper.TestMapper;
import me.pingcai.util.Lister;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class TestRepository {

    @Resource
    private TestMapper testMapper;

    public Test selectByName(Test test) {
        TestExample example = new TestExample();
        example.or().andNameEqualTo(test.getName());
        return Lister.firstOfList(testMapper.selectByExample(example));
    }


    public int insert(Test test) {
        return testMapper.insert(test);
    }
}
