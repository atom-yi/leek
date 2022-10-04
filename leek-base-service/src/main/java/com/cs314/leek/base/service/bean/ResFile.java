package com.cs314.leek.base.service.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResFile {
    private String path;
    private long updateTime;
    private long expireTime;
}
