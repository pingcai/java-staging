package me.pingcai.service.impl;

import me.pingcai.dao.entity.Authority;
import me.pingcai.dao.mapper.RoleMapper;
import me.pingcai.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pingcai at 2018/6/14 10:54
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;


    @Override
    public List<Authority> selectAllAuthorityByUserId(Long id) {
        return roleMapper.selectAllAuthorityByUserId(id);
    }
}
