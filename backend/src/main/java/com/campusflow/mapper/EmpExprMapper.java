package com.campusflow.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.campusflow.pojo.EmpExpr;

import java.util.List;

@Mapper
public interface EmpExprMapper {


    void insertBatch(List<EmpExpr> exprs);

    void deleteByEmpIds(List<Integer> empIds);


    List<EmpExpr> getByEmpId(Integer id);
}
