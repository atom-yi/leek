package com.cs314.leek.vo;

import com.cs314.leek.enums.HttpStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResp<T> {
    private int code;
    private String message;
    private T data;

    /**
     * 请求成功，指定响应信息
     *
     * @return 成功响应
     */
    public RestResp<T> success() {
        this.code = HttpStatus.SUCCESS.getCode();
        this.message = HttpStatus.SUCCESS.getMessage();
        return this;
    }

    /**
     * 请求成功，指定返回数据
     *
     * @param data 响应数据
     * @return 响应数据
     */
    public RestResp<T> success(T data) {
        success();
        this.data = data;
        return this;
    }

    /**
     * 请求失败，指定失败信息
     *
     * @param errMsg 失败信息
     * @return 失败信息
     */
    public RestResp<T> failed(String errMsg) {
        this.code = HttpStatus.FAILED.getCode();
        this.message = errMsg;
        return this;
    }

    /**
     * 生成默认的成功请求
     *
     * @return 请求成功
     */
    public static RestResp<Void> defaultSuccess() {
        RestResp<Void> restResp = new RestResp<>();
        return restResp.success();
    }
}
