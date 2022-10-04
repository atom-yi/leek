package com.cs314.leek.base.service.dao;

import com.cs314.leek.base.service.bean.ResFile;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ResFileDao {
    @Select("select path, update_time as updateTime from res_file where path = #{path}")
    ResFile findFileByPath(@Param("path") String path);

    @Insert("insert into res_file(path, create_time, expire_time) " +
            "values(#{path}, #{updateTime}, #{expireTime})")
    void insertResFile(ResFile resFile);

    @Delete("delete from res_file where path = #{path}")
    int deleteByResFile(String path);

    @Update("update res_file set update_ime = #{updateTime}, expire_time = #{expireTime} " +
            "where path = #{path}")
    void updateResFile(ResFile resFile);
}
