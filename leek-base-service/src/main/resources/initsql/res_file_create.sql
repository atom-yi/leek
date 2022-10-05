create table if not exists res_file
(
    `filepath`    varchar(500) not null unique comment '文件路径',
    `update_time` LONG         not null comment '创建时间毫秒时间戳',
    `expire_time` LONG         not null comment '过期时间毫秒时间戳'
);