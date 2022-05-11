package com.ling.ems_vue.service;

import com.ling.ems_vue.entity.User;

public interface UserService {

    //用户注册
    void register(User user);

    //用户登陆
    User login(User user);
}
