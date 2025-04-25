package com.campusflow.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.campusflow.mapper.ClazzsMapper;
import com.campusflow.mapper.StudentMapper;
import com.campusflow.pojo.Clazz;
import com.campusflow.pojo.PageResult;
import com.campusflow.service.ClazzsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzsServiceImpl implements ClazzsService {

    private final ClazzsMapper clazzsMapper;
    private final StudentMapper studentMapper;

    public ClazzsServiceImpl(ClazzsMapper clazzsMapper, StudentMapper studentMapper) {
        this.clazzsMapper = clazzsMapper;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<Clazz> findAll() {
        return clazzsMapper.findAll();
    }

    /**
     * 删除班级前先把班级下的学生迁移到“暂无对应班级”，避免留下脏数据
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        studentMapper.clearClazzId(id);
        clazzsMapper.delete(id);
    }

    @Override
    public PageResult page(Integer page, Integer pageSize, String name, LocalDate begin, LocalDate end) {
        Long total = clazzsMapper.getCountByCondition(name, begin, end);
        Integer start = (page - 1) * pageSize;
        List<Clazz> rows = clazzsMapper.pageList(start, pageSize, name, begin, end);
        return new PageResult(total, rows);
    }

    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzsMapper.insert(clazz);
    }

    @Override
    public Clazz findById(Integer id) {
        return clazzsMapper.findById(id);
    }

    @Override
    public void save(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzsMapper.update(clazz);
    }
}
