package com.campusflow.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import com.campusflow.pojo.Result;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("系统异常", e);
        return Result.error("服务器开小差了，请稍后重试");
    }

    /**
     * 参数校验失败，返回第一条校验提示
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("请求参数不合法");
        return Result.error(message);
    }

    /**
     * 唯一约束冲突，例如用户名、手机号重复
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.warn("唯一约束冲突: {}", e.getMessage());
        String message = e.getMessage() == null ? "" : e.getMessage();
        int index = message.indexOf("Duplicate entry");
        if (index >= 0) {
            String[] arr = message.substring(index).split(" ");
            if (arr.length > 2) {
                return Result.error(arr[2] + "已存在");
            }
        }
        return Result.error("数据已存在，请勿重复提交");
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result handleMaxUploadSizeException(MaxUploadSizeExceededException e) {
        return Result.error("上传文件过大，单个文件不能超过 10MB");
    }
}
