package com.campusflow.service;

import com.campusflow.pojo.Emp;
import com.campusflow.pojo.LoginInfo;
import com.campusflow.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    PageResult page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    void save(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    void update(Emp emp);

    List<Emp> list();

    LoginInfo login(Emp emp);
}
