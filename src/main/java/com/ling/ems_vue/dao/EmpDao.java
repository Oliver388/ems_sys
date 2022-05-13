package com.ling.ems_vue.dao;

import com.ling.ems_vue.entity.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpDao {

    List<Emp> findAll();
}
