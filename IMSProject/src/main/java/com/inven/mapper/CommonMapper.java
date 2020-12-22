package com.inven.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface CommonMapper {
	/* @Mapper는 mapper의 namespace를 Mapper.java 의 경로를 적어야한다.*/ 
	public Map<String, Object> loginCheck(Map<String, Object> map);
}
