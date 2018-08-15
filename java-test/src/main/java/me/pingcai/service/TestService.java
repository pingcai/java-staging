package me.pingcai.service;

import me.pingcai.dao.entity.Test;
import sun.util.resources.ga.LocaleNames_ga;

import java.util.Map;

public interface TestService {

    Test insertIfNotExist(Test test);

    boolean deleteById(Long id);

    boolean update(Test test);

    void fillTest(Map<String,String> data, Test test);

    void returnVoid();

    void returnVoid2();

    Test getById(Long l);

    Test selectByName(String name);
}
