package com.inven.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {
	/* @Mapper는 mapper의 namespace를 Mapper.java 의 경로를 적어야한다.*/ 
	public Map<String, Object> loginCheck(Map<String, Object> map);
}
