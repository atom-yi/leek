package com.cs314.leek.base.service.config;

import com.cs314.leek.util.MinioUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitConfig implements ApplicationRunner {
    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.account}")
    private String account;
    @Value("${minio.password}")
    private String password;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        MinioUtil.init(endpoint, account, password);
    }
}
