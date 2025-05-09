package com.campusflow.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.campusflow.pojo.Result;
import com.campusflow.utils.AliyunOSSOperator;

import java.util.Set;

@Slf4j
@RestController
public class UploadController {

    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("jpg", "jpeg", "png", "gif", "webp");

    private final AliyunOSSOperator aliyunOSSOperator;

    public UploadController(AliyunOSSOperator aliyunOSSOperator) {
        this.aliyunOSSOperator = aliyunOSSOperator;
    }

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            return Result.error("请选择要上传的图片");
        }

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename == null || !originalFilename.contains(".")
                ? ""
                : originalFilename.substring(originalFilename.lastIndexOf('.') + 1).toLowerCase();
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            return Result.error("仅支持 jpg、jpeg、png、gif、webp 格式的图片");
        }

        log.info("文件上传：{}", originalFilename);
        String url = aliyunOSSOperator.upload(file.getBytes(), originalFilename);
        return Result.success(url);
    }
}
