package com.campusflow.service;

import org.springframework.format.annotation.DateTimeFormat;
import com.campusflow.pojo.Clazz;
import com.campusflow.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface ClazzsService {

    List<Clazz> findAll();

    void delete(Integer id);

    PageResult page(Integer page, Integer pageSize, String name, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end);

    void add(Clazz clazz);

    Clazz findById(Integer id);

    void save(Clazz clazz);
}
