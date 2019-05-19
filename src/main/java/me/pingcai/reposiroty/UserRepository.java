package me.pingcai.reposiroty;

import com.github.pagehelper.PageHelper;
import me.pingcai.domain.entity.User;
import me.pingcai.domain.entity.UserExample;
import me.pingcai.dao.mapper.UserMapper;
import me.pingcai.util.ListUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserRepository {

    @Resource
    private UserMapper userMapper;

    public User selectByName(String name) {
        UserExample example = new UserExample();
        example.or().andNameEqualTo(name);
        return ListUtils.firstOfList(userMapper.selectByExample(example));
    }


    public int insert(User user) {
        return userMapper.insert(user);
    }

    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }


    public List<User> selectUserByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize,false);
        UserExample example = new UserExample();
        return userMapper.selectByExample(example);
    }
}
