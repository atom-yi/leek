package com.cs314.leek.base.service.service;

import com.cs314.leek.constants.TimeConst;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ServerService {
    /**
     * 获取服务器时间
     *
     * @return 服务器时间 yyyy-MM-dd HH:mm:ss
     */
    public String getServerTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(TimeConst.DEFAULT_FORMATTER);
    }
}
