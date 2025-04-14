package com.campusflow.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 只允许登录/新增时写入，任何接口都不会把密码返回给前端
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private String password;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotNull(message = "性别不能为空")
    private Integer gender;

    @NotBlank(message = "手机号不能为空")
    private String phone;

    private Integer job; //职位, 1:辅导员,2:任课教师,3:教务管理员,4:系主任,5:招生专员
    private Integer salary;
    private String image;
    private LocalDate entryDate;
    private Integer deptId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private String deptName; //关联的部门名称

    private List<EmpExpr> exprList; //员工工作经历
}
