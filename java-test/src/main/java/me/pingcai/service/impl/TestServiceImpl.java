package me.pingcai.service.impl;

import me.pingcai.dao.entity.Test;
import me.pingcai.enums.HttpError;
import me.pingcai.exception.ApiException;
import me.pingcai.reposiroty.TestRepository;
import me.pingcai.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    private boolean check(Test test) {
        return test != null && test.getName() != null;
    }


}
