package com.helltractor.demo.mapper;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FooMapper {

    /**
     * 获取当前时间
     *
     * @return
     */
    LocalDateTime now();

}
