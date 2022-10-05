package com.cs314.leek.base.service.bean;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResFile {
    private String filepath;
    private long updateTime;
    private long expireTime;
}
