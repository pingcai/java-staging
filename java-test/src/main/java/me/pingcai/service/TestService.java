package me.pingcai.service;

import me.pingcai.dao.entity.Test;

public interface TestService {
    public Test insertIfNotExist(Test test);
}
