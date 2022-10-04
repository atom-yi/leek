package com.cs314.leek.base.service.controller;

import com.cs314.leek.base.service.bean.ResFile;
import com.cs314.leek.vo.RestResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.simpleframework.xml.Path;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 文件资源管理
 */
@Api(tags = "文件管理")
@RestController("/base-service/res-file")
public class ResFileController {
    @ApiOperation("查看文件信息")
    @GetMapping(value = "/show/{path}")
    public void show(@PathVariable("path") String path) {
        // todo: 返回文件流
    }

    @ApiOperation("上传文件")
    @PutMapping(value = "/upload")
    public RestResp<Void> uploadFile(@RequestParam MultipartFile file, HttpServletRequest req) {
        // todo: 上传文件到 Minio
        return RestResp.defaultSuccess();
    }

    @ApiOperation("分页查询文件信息")
    @GetMapping(value = "/query-with-page")
    public RestResp<List<ResFile>> queryWithPage() {
        // todo: 分页查询文件
        return new RestResp<List<ResFile>>().success();
    }

    @ApiOperation("删除文件")
    @DeleteMapping(value = "/delete")
    public RestResp<Void> delete() {
        // todo: 删除文件
        return RestResp.defaultSuccess();
    }
}
