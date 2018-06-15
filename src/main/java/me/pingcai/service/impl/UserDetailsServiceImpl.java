package me.pingcai.service.impl;

import me.pingcai.dao.entity.Authority;
import me.pingcai.dao.entity.User;
import me.pingcai.security.mapper.SimpleAuthoritiesMapper;
import me.pingcai.service.RoleService;
import me.pingcai.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * Created by pingcai at 2018/6/1 17:46
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private SimpleAuthoritiesMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.selectByNickName(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("user not found");
        }

        List<Authority> loadAuthorities = roleService.selectAllAuthorityByUserId(user.getId());

        org.springframework.security.core.userdetails.User seUser =
                new org.springframework.security.core.userdetails.User(
                        username, user.getPassword(), mapper.mapAuthorities(loadAuthorities));

        return seUser;
    }


}
