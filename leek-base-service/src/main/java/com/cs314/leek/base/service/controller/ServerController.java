package com.cs314.leek.base.service.controller;

import com.cs314.leek.base.service.service.ServerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "服务器信息接口")
@RestController
@RequestMapping("/base-service")
public class ServerController {
    @Resource
    private ServerService serverService;

    @GetMapping(value = "/server-time")
    @ApiOperation(value = "查询服务器实际时间", response = String.class)
    public String getServerTime() {
        return serverService.getServerTime();
    }
}
