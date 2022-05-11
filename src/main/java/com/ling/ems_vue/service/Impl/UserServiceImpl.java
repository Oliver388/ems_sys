package com.ling.ems_vue.service.Impl;

import com.ling.ems_vue.dao.UserDao;
import com.ling.ems_vue.entity.User;
import com.ling.ems_vue.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public void register(User user) {
        //0 根据用户输入的用户名判断是否存在
        User UserDB = userDao.findByUsername(user.getUsername());
        if(UserDB==null){
            //1 生成用户状态
            user.setStatus("已激活");
            //2 设置用户注册时间
            user.setRegisterTime(new Date());
            //3 调用DAO
            userDao.save(user);
        }else {
            throw new RuntimeException("该用户已存在!");
        }

    }

    @Override
    public User login(User user) {
        //1 根据用户输入的用户名进行查询
        User UserDB = userDao.findByUsername(user.getUsername());
        if (ObjectUtils.isEmpty(UserDB)){
            //2 比较密码
            if (UserDB.getPassword().equals(user.getPassword())){
                return UserDB;
            }else {
                throw new RuntimeException("密码输入不正确");
            }
        }else {
            throw new RuntimeException("该用户未注册");
        }
    }


}
