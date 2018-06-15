package me.pingcai.security.provider;


import me.pingcai.dao.entity.Authority;
import me.pingcai.dao.entity.User;
import me.pingcai.security.mapper.SimpleAuthoritiesMapper;
import me.pingcai.security.token.NickNameAuthenticationToken;
import me.pingcai.service.RoleService;
import me.pingcai.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * Created by pingcai at 2018/6/13 14:49
 */
public class NickNameProvider implements AuthenticationProvider {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource(name = "simpleAuthoritiesMapper")
    SimpleAuthoritiesMapper authorityMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        NickNameAuthenticationToken token = (NickNameAuthenticationToken) authentication;

        User user = userService.selectByNickName((String) token.getPrincipal());

        // 用户不存在
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException(String.format("user %s not exists", token.getPrincipal()));
        }

        if (!Objects.equals(String.valueOf(authentication.getCredentials()), user.getPassword())) {
            throw new BadCredentialsException("password is not match");
        }

        List<Authority> authorities = roleService.selectAllAuthorityByUserId(user.getId());

        NickNameAuthenticationToken toReturn = new NickNameAuthenticationToken(
                token.getPrincipal(), token.getCredentials(), authorityMapper.mapAuthorities(authorities));

        return toReturn;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return NickNameAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
