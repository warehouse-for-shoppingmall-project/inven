package com.inven.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RequestMapper {

	// 방금 만든 sql문을 접근하는 함수를 만들어봐
	public List<Map<String, Object>> searchTitle(Map<String, Object> map);
	public List<Map<String, Object>> searchDetail(Map<String, Object> map);
	public List<Map<String, Object>> searchWhere(Map<String, Object> map);

	// Ajax Method+
	public int upStatus(Map<String, Object> map);

	
//	public List<Map<String, Object>> selectAll(Map<String, Object> map);
//	public List<Map<String, Object>> searchProductCode(Map<String, Object> map);
//	public List<Map<String, Object>> searchRequestDate(Map<String, Object> map);
//	public List<Map<String, Object>> searchRequestDates(Map<String, Object> map);
//	public List<Map<String, Object>> searchRequestStatus(Map<String, Object> map);

}
