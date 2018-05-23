package com.neverend.service;

import com.neverend.controller.UserController;
import com.neverend.entity.User;

public interface UserService {
    int saveUser(User user);

    User login(User user);
}
