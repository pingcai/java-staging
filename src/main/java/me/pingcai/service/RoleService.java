package me.pingcai.service;

import me.pingcai.dao.entity.Authority;

import java.util.List;

/**
 * Created by pingcai at 2018/6/14 10:53
 */
public interface RoleService {

    /**
     * 通过用户id加载其所有权限
     * @param id
     * @return
     */
    List<Authority> selectAllAuthorityByUserId(Long id);

}
