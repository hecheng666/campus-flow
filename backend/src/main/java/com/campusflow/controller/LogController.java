package com.campusflow.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.campusflow.pojo.PageResult;
import com.campusflow.pojo.Result;
import com.campusflow.service.LogService;

@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult pageResult = logService.page(page, pageSize);
        return Result.success(pageResult);
    }
}
