package me.pingcai.security.mapper;

import com.google.common.collect.Lists;
import me.pingcai.dao.entity.Authority;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * Created by pingcai at 2018/6/14 11:26
 */
@Component("simpleAuthoritiesMapper")
public class SimpleAuthoritiesMapper implements GrantedAuthoritiesMapper {

    @Override
    public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities;
    }

    public Collection<? extends GrantedAuthority> mapAuthorities(List<Authority> authorities) {
        final List<GrantedAuthority> toReturn = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(authorities)) {
            authorities.forEach((authority -> {
                toReturn.add(new SimpleGrantedAuthority(authority.getAuthorityName()));
            }));
        }
        return toReturn;
    }

}
