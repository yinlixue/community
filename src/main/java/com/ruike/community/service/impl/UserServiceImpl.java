package com.ruike.community.service.impl;

import com.ruike.community.dao.UserMapper;
import com.ruike.community.entity.User;
import com.ruike.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserById(int userId) {
        return userMapper.selectUserById(userId);
    }
}
