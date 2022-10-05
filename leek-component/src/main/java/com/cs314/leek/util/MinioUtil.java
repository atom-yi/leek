package com.cs314.leek.util;

import io.minio.MinioClient;
import io.minio.PutObjectOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Mino 操作
 */
public class MinioUtil {
    private static final String BUCKET = "leek";
    private static final int PART_SIZE = 10 * 1024 * 1024;
    private static MinioClient client;

    /**
     * 初始化 minio
     */
    public synchronized static void init(String endPoint, String account, String pwd) throws IOException {
        try {
            client = new MinioClient(endPoint, account, pwd);
            if (!client.bucketExists(BUCKET)) {
                client.makeBucket(BUCKET);
            }
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * 上传文件
     *
     * @param remoteFilePath 远程文件
     * @param localFilePath  本地文件
     * @param fileSize       文件大小
     * @throws IOException 上传文件失败异常
     */
    public static void uploadFile(String remoteFilePath, String localFilePath, long fileSize) throws IOException {
        uploadFile(remoteFilePath, new FileInputStream(localFilePath), new File(localFilePath).length());
    }

    /**
     * 上传文件
     *
     * @param remoteFilePath 远程文件路径
     * @param input          输入流
     * @param fileSize       文件大小
     * @throws IOException 上传异常
     */
    public static void uploadFile(String remoteFilePath, InputStream input, long fileSize) throws IOException {
        try {
            PutObjectOptions options = new PutObjectOptions(fileSize, PART_SIZE);
            client.putObject(BUCKET, remoteFilePath, input, options);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * 删除文件
     *
     * @param remoteFilePath 远程文件路径
     * @throws IOException 删除失败异常
     */
    public static void deleteFile(String remoteFilePath) throws IOException {
        try {
            client.removeObject(BUCKET, remoteFilePath);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * 获取文件内容
     *
     * @param path 文件路径
     * @return 文件内容
     * @throws IOException 获取文件内容失败异常
     */
    public static InputStream getFile(String path) throws IOException {
        try {
            return client.getObject(BUCKET, path);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }
}
