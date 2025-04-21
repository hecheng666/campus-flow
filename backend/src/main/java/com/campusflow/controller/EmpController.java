package com.campusflow.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.campusflow.pojo.Emp;
import com.campusflow.pojo.EmpExpr;
import com.campusflow.pojo.PageResult;
import com.campusflow.pojo.Result;
import com.campusflow.service.EmpService;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/emps")    // 员工模块的请求路径前缀
@RestController             // 标志为Controller，返回JSON格式数据,相当于@Controller+@ResponseBody
@Slf4j                      // 日志记录，自动记录类名，方便定位日志来源
public class EmpController {
    @Autowired              // 自动注入EmpService Bean
    private EmpService empService;

    /**
     * 分页条件查询
     * @param page 当前页码，默认值为1
     * @param pageSize 每页显示条数，默认值为10
     * @param name 姓名，可选参数
     * @param gender 性别，可选参数，1:男, 2:女
     * @param begin 入职日期开始范围，可选参数，格式yyyy-MM-dd
     * @param end 入职日期结束范围，可选参数，格式yyyy-MM-dd
     * @return
     */
    @GetMapping //localhost:8080/emps?page=1&pageSize=10&name=张三&gender=1&begin=2020-01-01&end=2023-01-01
    public Result page(@RequestParam(value="page",defaultValue = "1")Integer page,
                       @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
                       String name, //省略@RequestParam也可以，因为与URL查询参数名一致
                       Integer gender,  //省略@RequestParam也可以，因为与URL查询参数名一致
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin, //省略@RequestParam也可以，因为与URL查询参数名一致
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end //@DateTimeFormat注解用于将URL查询参数转换为LocalDate类型
                       )
    {
        log.info("分页条件查询：{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);

        PageResult pageResult = empService.page(page, pageSize,name,gender,begin,end);

        return Result.success(pageResult);
    }

    /**
     * 添加员工
     * @param emp 员工对象，包含新增的员工信息
     * @return
     */
    @PostMapping
    public Result save(@Valid @RequestBody Emp emp){   //请求体（JSON 格式）
        log.info("新增员工：{}",emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 删除员工
     * @param ids 员工ID列表，用于批量删除
     * @return
     */
    @DeleteMapping          //localhost:8080/emps?ids=1,2,3
    public Result delete(@RequestParam List<Integer> ids){  //查询参数（URL 路径中的占位符）
        log.info("批量删除员工：{}",ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 修改员工数据时，回显数据
     * @param id 员工ID，用于查询指定员工的详细信息
     * @return
     */
    @GetMapping("/{id}")    //localhost:8080/emps/1
    public Result getById(@PathVariable Integer id){    //路径变量（URL 路径中的占位符）
        log.info("根据ID查询：{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    /**
     * 修改员工数据
     * @param emp 员工对象，包含修改后的员工信息
     * @return
     */
    @PutMapping              //localhost:8080/emps
    public Result update(@RequestBody Emp emp){
        log.info("修改员工：{}",emp);
        empService.update(emp);
        return Result.success();
    }

    /**
     * 查询所有员工
     * @return
     */
    @GetMapping("/list")        //localhost:8080/emps/list
    public Result list(){
        log.info("查询所有员工");
        List<Emp> list = empService.list();
        return Result.success(list);
    }
}
