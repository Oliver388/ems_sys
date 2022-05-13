package com.ling.ems_vue.service.Impl;

import com.ling.ems_vue.dao.EmpDao;
import com.ling.ems_vue.entity.Emp;
import com.ling.ems_vue.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDao empDao;


    @Override
    public List<Emp> findAll() {

        return empDao.findAll();
    }
}
