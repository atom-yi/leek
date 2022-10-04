package com.cs314.leek.util;

import io.minio.MinioClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Mino 操作
 */
public class MinioUtil {
    private static final String BUCKET = "leek";
    private static MinioClient client;

    /**
     * 初始化 minio
     */
    public synchronized static void init(String endPoint, String account, String pwd) {
        try {
            client = new MinioClient(endPoint, account, pwd);
            if (!client.bucketExists(BUCKET)) {
                client.makeBucket(BUCKET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     *
     * @param remoteFilePath 远程文件
     * @param localFilePath  本地文件
     * @throws IOException 上传文件失败异常
     */
    public void uploadFile(String remoteFilePath, String localFilePath) throws IOException {
        uploadFile(remoteFilePath, new FileInputStream(localFilePath));
    }

    /**
     * 上传文件
     *
     * @param remoteFilePath 远程文件路径
     * @param input          输入流
     * @throws IOException 上传异常
     */
    public void uploadFile(String remoteFilePath, InputStream input) throws IOException {
        try {
            client.putObject(BUCKET, remoteFilePath, input, null);
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
    private void deleteFile(String remoteFilePath) throws IOException {
        try {
            client.removeObject(BUCKET, remoteFilePath);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }
}
