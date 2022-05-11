package com.ling.ems_vue.dao;

import com.ling.ems_vue.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper //创建DAO对象
public interface UserDao {
    void save(User user);

    User findByUsername(String username);
}
