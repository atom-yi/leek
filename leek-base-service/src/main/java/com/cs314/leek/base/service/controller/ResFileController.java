package com.cs314.leek.base.service.controller;

import com.cs314.leek.base.service.bean.ResFile;
import com.cs314.leek.base.service.service.ResFileService;
import com.cs314.leek.base.service.vo.ResFileVo;
import com.cs314.leek.vo.RestResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文件资源管理
 */
@Slf4j
@Api(tags = "文件管理")
@RestController
@RequestMapping("/base-service/res-file")
public class ResFileController {
    @Resource
    private ResFileService resFileService;

    @ApiOperation("查看文件信息")
    @GetMapping(value = "/show/**")
    public void show(HttpServletRequest req, HttpServletResponse resp) {
        String uri = req.getRequestURI();
        int firstIdx = uri.indexOf("show") + 4;
        String path;
        if (firstIdx >= uri.length()) {
            path = StringUtils.EMPTY;
        } else {
            path = uri.substring(firstIdx);
        }
        log.info("show file start, path:{}", path);
        resFileService.show(path, resp);
        log.info("show file end");
    }

    @ApiOperation("上传文件")
    @PutMapping(value = "/upload")
    public RestResp<Void> uploadFile(@RequestParam MultipartFile file, HttpServletRequest req) {
        log.info("upload file start");
        String filepath = req.getParameter("filepath");
        RestResp<Void> restResp = resFileService.uploadFile(file, filepath);
        log.info("upload file done. result:{}", restResp);
        return restResp;
    }

    @ApiOperation("列出所有文件信息")
    @GetMapping(value = "/list")
    public RestResp<List<ResFileVo>> list() {
        return resFileService.list();
    }

    @ApiOperation("分页查询文件信息")
    @GetMapping(value = "/query-with-page")
    public RestResp<List<ResFile>> queryWithPage() {
        // todo: 分页查询文件
        return new RestResp<List<ResFile>>().success();
    }

    @ApiOperation("删除文件")
    @DeleteMapping(value = "/delete")
    public RestResp<Void> delete(@RequestParam("filepath") String filePath) {
        return resFileService.deleteFile(filePath);
    }
}
