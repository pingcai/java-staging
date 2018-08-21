package me.pingcai.service;

import me.pingcai.dao.entity.User;

public interface UserService {
    User insertIfNotExist(User user);
}
