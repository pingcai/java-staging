package me.pingcai.service.impl;

import me.pingcai.dao.entity.Test;
import me.pingcai.enums.HttpError;
import me.pingcai.exception.ApiException;
import me.pingcai.reposiroty.TestRepository;
import me.pingcai.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestRepository testRepository;

    public Test insertIfNotExist(Test test){
        if(!check(test)){
            throw ApiException.create(HttpError.INVALID_PARAM);
        }

        Test dbTest = testRepository.selectByName(test);
        if(dbTest != null){
            throw ApiException.create(HttpError.EXIST);
        }

        testRepository.insert(test);
        return test;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean update(Test test) {
        return false;
    }

    @Override
    public void fillTest(Map<String, String> data, Test test) {

    }

    @Override
    public void returnVoid() {

    }

    @Override
    public void returnVoid2() {

    }

    @Override
    public Test getById(Long l) {
        return null;
    }

    @Override
    public Test selectByName(String name) {
        Test test = new Test();
        test.setName(name);
        return testRepository.selectByName(test);
    }

    private boolean check(Test test) {
        return test != null && test.getName() != null;
    }


}
