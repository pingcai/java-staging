package me.pingcai.reposiroty;

import me.pingcai.dao.entity.User;
import me.pingcai.dao.entity.UserExample;
import me.pingcai.dao.mapper.UserMapper;
import me.pingcai.util.Lister;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserRepository {

    @Resource
    private UserMapper userMapper;

    public User selectByName(String name) {
        UserExample example = new UserExample();
        example.or().andNameEqualTo(name);
        return Lister.firstOfList(userMapper.selectByExample(example));
    }


    public int insert(User user) {
        return userMapper.insert(user);
    }

    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
