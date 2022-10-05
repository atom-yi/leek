package com.cs314.leek.base.service.service;

import com.alibaba.fastjson.JSON;
import com.cs314.leek.base.service.bean.ResFile;
import com.cs314.leek.base.service.dao.ResFileDao;
import com.cs314.leek.base.service.vo.ResFileVo;
import com.cs314.leek.constants.TimeConst;
import com.cs314.leek.util.MinioUtil;
import com.cs314.leek.vo.RestResp;
import com.google.common.io.ByteStreams;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

@Slf4j
@Service
public class ResFileService {
    /**
     * 最大过期时间：9999-00-01 00:00:00
     */
    private static final long MAX_EXPIRE_TIME = 253368057600000L;

    @Resource
    private ResFileDao resFileDao;

    /**
     * 显示远程文件内容
     *
     * @param path 远程文件路径
     * @param resp 影响流
     */
    public void show(String path, HttpServletResponse resp) {
        RestResp<String> restResp = new RestResp<>();
        restResp.failed("File Not Found!");
        if (StringUtils.isBlank(path)) {
            writeToJsonResp(restResp, resp);
            return;
        }
        path = path.startsWith("/") ? path : "/" + path;

        ResFile resFile = resFileDao.findFileByPath(path);
        if (Objects.isNull(resFile)) {
            writeToJsonResp(restResp, resp);
            return;
        }

        try {
            ByteStreams.copy(MinioUtil.getFile(path), resp.getOutputStream());
        } catch (IOException e) {
            restResp.failed(e.getMessage());
            writeToJsonResp(restResp, resp);
        }
    }

    private void writeToJsonResp(Object obj, HttpServletResponse resp) {
        try {
            ServletOutputStream output = resp.getOutputStream();
            output.write(JSON.toJSONString(obj).getBytes(StandardCharsets.UTF_8));
            output.flush();
        } catch (IOException e) {
            log.error("get output stream failed when invoke ResFileService.show()");
        }
    }

    /**
     * 上传文件
     *
     * @param file     文件内容
     * @param filepath 文件路径
     * @return 上传结果
     */
    public RestResp<Void> uploadFile(MultipartFile file, String filepath) {
        if (file.isEmpty()) {
            return RestResp.defaultFailed("empty file!");
        }
        if (StringUtils.isBlank(filepath)) {
            return RestResp.defaultFailed("filename is empty!");
        }
        if (!filepath.startsWith("/")) {
            return RestResp.defaultFailed("invalid filepath");
        }

        ResFile resFile = buildResFileByPath(filepath);
        try {
            resFileDao.insertResFile(resFile);
        } catch (Exception e) {
            return RestResp.defaultFailed(e.getMessage());
        }

        try {
            MinioUtil.uploadFile(filepath, file.getInputStream(), file.getSize());
        } catch (IOException e) {
            return RestResp.defaultFailed(e.getMessage());
        }
        return RestResp.defaultSuccess();
    }

    private ResFile buildResFileByPath(String filepath) {
        return ResFile.builder()
                .filepath(filepath)
                .updateTime(System.currentTimeMillis())
                .expireTime(MAX_EXPIRE_TIME)
                .build();
    }

    /**
     * 删除文件
     *
     * @param filePath 文件路径
     * @return 删除结果
     */
    public RestResp<Void> deleteFile(String filePath) {
        resFileDao.deleteByResFile(filePath);
        try {
            MinioUtil.deleteFile(filePath);
        } catch (IOException e) {
            return RestResp.defaultFailed(e.getMessage());
        }
        return RestResp.defaultSuccess();
    }

    /**
     * 列出所有文件悉尼下
     *
     * @return 文件信息
     */
    public RestResp<List<ResFileVo>> list() {
        List<ResFile> resFiles = resFileDao.list();
        List<ResFileVo> resFileVoList = new ArrayList<>();
        resFiles.forEach(resFile -> {
            ResFileVo resFileVo = new ResFileVo();
            resFileVo.setUpdateTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(resFile.getUpdateTime()), TimeZone.getDefault().toZoneId()).format(TimeConst.DEFAULT_FORMATTER));
            resFileVo.setExpireTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(resFile.getExpireTime()),
                    TimeZone.getDefault().toZoneId()).format(TimeConst.DEFAULT_FORMATTER));
            resFileVo.setFilepath(resFile.getFilepath());
            resFileVoList.add(resFileVo);
        });
        return RestResp.successWithData(resFileVoList);
    }
}
