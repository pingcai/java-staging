package me.pingcai.service;

import me.pingcai.dao.entity.Test;

/**
 * Created by pingcai at 2018/6/7 17:53
 */
public interface TestService {

    Test selectByPrimaryKey(Long id);
}
