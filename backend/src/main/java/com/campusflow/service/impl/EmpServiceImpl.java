package com.campusflow.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import com.campusflow.mapper.EmpExprMapper;
import com.campusflow.mapper.EmpMapper;
import com.campusflow.pojo.*;
import com.campusflow.service.EmpService;
import com.campusflow.utils.JwtUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class EmpServiceImpl implements EmpService {

    private final EmpMapper empMapper;
    private final EmpExprMapper empExprMapper;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public EmpServiceImpl(EmpMapper empMapper,
                          EmpExprMapper empExprMapper,
                          JwtUtils jwtUtils,
                          PasswordEncoder passwordEncoder) {
        this.empMapper = empMapper;
        this.empExprMapper = empExprMapper;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 分页条件查询
     */
    @Override
    public PageResult page(Integer page,
                           Integer pageSize,
                           String name,
                           Integer gender,
                           LocalDate begin,
                           LocalDate end) {
        // 总数和列表使用同一组筛选条件
        Long total = empMapper.getCountByCondition(name, gender, begin, end);
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.pageList(start, pageSize, name, gender, begin, end);
        for (Emp row : rows) {
            row.setExprList(empExprMapper.getByEmpId(row.getId()));
        }
        return new PageResult(total, rows);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        if (emp.getPassword() != null && !emp.getPassword().isBlank()) {
            emp.setPassword(passwordEncoder.encode(emp.getPassword()));
        }
        empMapper.insert(emp);

        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            for (EmpExpr expr : exprList) {
                expr.setEmpId(emp.getId());
            }
            empExprMapper.insertBatch(exprList);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByIds(List<Integer> ids) {
        empExprMapper.deleteByEmpIds(ids);
        empMapper.deleteByIds(ids);
    }

    @Override
    public Emp getById(Integer id) {
        List<EmpExpr> exprList = empExprMapper.getByEmpId(id);
        Emp emp = empMapper.selectById(id);
        emp.setExprList(exprList);
        return emp;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        if (emp.getPassword() != null && !emp.getPassword().isBlank()) {
            emp.setPassword(passwordEncoder.encode(emp.getPassword()));
        }
        empMapper.update(emp);

        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> list() {
        List<Emp> list = empMapper.findAll();
        for (Emp emp : list) {
            emp.setExprList(empExprMapper.getByEmpId(emp.getId()));
        }
        return list;
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp e = empMapper.selectByUsername(emp.getUsername());
        if (e == null || !passwordMatches(emp.getPassword(), e)) {
            return null;
        }

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", e.getId());
        dataMap.put("username", e.getUsername());
        String jwt = jwtUtils.generateJwt(dataMap);

        return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);
    }

    /**
     * 兼容历史明文密码：首次登录成功后自动升级为 BCrypt
     */
    private boolean passwordMatches(String rawPassword, Emp stored) {
        String storedPassword = stored.getPassword();
        if (rawPassword == null || storedPassword == null) {
            return false;
        }
        if (storedPassword.startsWith("$2")) {
            return passwordEncoder.matches(rawPassword, storedPassword);
        }
        if (Objects.equals(rawPassword, storedPassword)) {
            empMapper.updatePassword(stored.getId(), passwordEncoder.encode(rawPassword));
            return true;
        }
        return false;
    }
}
