package com.ling.ems_vue.dao;

import com.ling.ems_vue.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    void save(User user);
}
