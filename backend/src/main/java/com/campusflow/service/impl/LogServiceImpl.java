package com.campusflow.service.impl;

import org.springframework.stereotype.Service;
import com.campusflow.mapper.OperateLogMapper;
import com.campusflow.pojo.OperateLog;
import com.campusflow.pojo.PageResult;
import com.campusflow.service.LogService;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    private final OperateLogMapper operateLogMapper;

    public LogServiceImpl(OperateLogMapper operateLogMapper) {
        this.operateLogMapper = operateLogMapper;
    }

    @Override
    public PageResult page(Integer page, Integer pageSize) {
        Long total = operateLogMapper.getCount();
        Integer start = (page - 1) * pageSize;
        List<OperateLog> rows = operateLogMapper.pageList(start, pageSize);
        return new PageResult(total, rows);
    }
}
