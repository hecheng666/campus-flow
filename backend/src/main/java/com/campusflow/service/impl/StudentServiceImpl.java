package com.campusflow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.campusflow.mapper.StudentMapper;
import com.campusflow.pojo.PageResult;
import com.campusflow.pojo.Student;
import com.campusflow.service.StudentService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public PageResult page(Integer page, Integer pageSize, String name, Integer degree, Integer clazzId) {
        Long total = studentMapper.getCountByCondition(name, degree, clazzId);
        Integer start = (page-1) * pageSize;
        List<Student> rows = studentMapper.pageList(start,pageSize,name,degree,clazzId);
        PageResult pageResult = new PageResult(total,rows);
        return pageResult;
    }

    @Override
    public Student findById(Integer id) {
        return studentMapper.findById(id);
    }

    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    @Override
    public void add(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        student.setCreateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public void save(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void violationHandle(Integer id, Integer score) {
        studentMapper.updateViolation(id,score);
    }

}
