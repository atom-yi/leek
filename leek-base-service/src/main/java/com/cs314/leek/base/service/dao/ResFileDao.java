package com.cs314.leek.base.service.dao;

import com.cs314.leek.base.service.bean.ResFile;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ResFileDao {
    @Select("select filepath, update_time as updateTime from res_file where filepath = #{filepath}")
    ResFile findFileByPath(@Param("filepath") String path);

    @Insert("insert into res_file(filepath, update_time, expire_time) " +
            "values(#{filepath}, #{updateTime}, #{expireTime}) " +
            "on duplicate key " +
            "update update_time=${updateTime}, expire_time = #{expireTime}")
    void insertResFile(ResFile resFile);

    @Delete("delete from res_file where filepath = #{filepath}")
    int deleteByResFile(String path);

    @Update("update res_file set update_time = #{updateTime}, expire_time = #{expireTime} " +
            "where filepath = #{filepath}")
    void updateResFile(ResFile resFile);

    @Select("select filepath, update_time as updateTime, expire_time as expireTime " +
            "from res_file")
    List<ResFile> list();
}
