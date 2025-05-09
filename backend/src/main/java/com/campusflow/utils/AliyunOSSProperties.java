package com.campusflow.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data     // 自动生成getter、setter、toString、equals、hashCode方法
@Component      // 标记为组件，用于被Spring容器管理
@ConfigurationProperties(prefix="aliyun.oss")   // 从配置文件中读取阿里云OSS相关配置
public class AliyunOSSProperties {
    private String endpoint;
    private String bucketName;
    private String region;

}
