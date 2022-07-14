package com.ling.ems_vue.service.Impl;

import com.ling.ems_vue.dao.EmpDao;
import com.ling.ems_vue.entity.Emp;
import com.ling.ems_vue.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDao empDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Emp> findAll() {
        return empDao.findAll();
    }

    @Override
    public void save(Emp emp) {
        empDao.save(emp);
    }

    @Override
    public void delete(String id) {
        empDao.delete(id);
    }

    @Override
    public Emp findOne(String id) {
        return empDao.findOne(id);
    }

    @Override
    public void update(Emp emp) {
        empDao.update(emp);
    }
}
