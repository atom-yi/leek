package com.cs314.leek.base.service.controller;

import com.cs314.leek.base.service.service.ServerTimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/base-service")
public class ServerTimeController {
    @Resource
    private ServerTimeService serverTimeService;

    /**
     * 获取服务器时间
     *
     * @return 服务器时间
     */
    @GetMapping(value = "/server-time")
    public String getServerTime() {
        return serverTimeService.getServerTime();
    }
}
