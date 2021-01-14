package com.inven.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface CommonMapper {
    int loginCheck(String pwd);
    int loginChange(Map<String, Object> map);
}
