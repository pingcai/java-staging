package me.pingcai.reposiroty;

import me.pingcai.dao.entity.User;
import me.pingcai.dao.entity.UserExample;
import me.pingcai.dao.enums.UserStatus;
import me.pingcai.dao.mapper.UserMapper;
import me.pingcai.util.Lister;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(propagation = Propagation.NESTED)
    public int freezeUser(User user){
        User dbUser = new User();
        dbUser.setId(user.getId());
        dbUser.setStatus(UserStatus.FREEZE);
        int effect = userMapper.updateByPrimaryKeySelective(dbUser);
        if(effect > 0){
            user.setStatus(UserStatus.FREEZE);
        }
        throw new RuntimeException();
    }

    @Transactional
    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }
}
