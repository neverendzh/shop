package com.neverend.service.impl;

import com.neverend.controller.UserController;
import com.neverend.entity.User;
import com.neverend.entity.UserExample;
import com.neverend.mapper.UserMapper;
import com.neverend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public int saveUser(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(user.getUsername());
        List<User> users =  userMapper.selectByExample(userExample);

        if (users.isEmpty()){
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            user.setState(0);
            user.setCode(uuid);
            userMapper.insert(user);
        }else {
            return 0;
        }
        return  user.getUid();
    }

    @Override
    public User login(User user) {
        UserExample userExample  = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(user.getUsername());
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList.isEmpty()){
            return null;
        }else {
            User user1 = userList.get(0);
            if (user.getPassword().equals(user1.getPassword())){
                return user1;
            }
        }
        return null;
    }
}
