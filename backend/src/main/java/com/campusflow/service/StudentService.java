package com.campusflow.service;

import com.campusflow.pojo.PageResult;
import com.campusflow.pojo.Student;

import java.util.List;

public interface StudentService {

    PageResult page(Integer page, Integer pageSize, String name, Integer degree, Integer clazzId);

    Student findById(Integer id);


    void delete(List<Integer> ids);

    void add(Student student);

    void save(Student student);

    void violationHandle(Integer id, Integer score);
}
