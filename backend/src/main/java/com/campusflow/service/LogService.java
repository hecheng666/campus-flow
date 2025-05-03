package com.campusflow.service;

import com.campusflow.pojo.PageResult;

public interface LogService {
    PageResult page(Integer page, Integer pageSize);
}
