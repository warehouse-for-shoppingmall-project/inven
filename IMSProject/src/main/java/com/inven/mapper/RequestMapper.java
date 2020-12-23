package com.inven.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RequestMapper {

	// select
	int searchCount(Map<String, Object> map);
	List<Map<String, Object>> searchDetail(Map<String, Object> map);
	List<Map<String, Object>> searchWhere(Map<String, Object> map);
	List<String> selectProductCode();
	String makeReqCode();

	// insert
	int addTitle(Map<String, Object> map);
	int addDetail(List<Map<String, Object>> list);

	// update
	int upStatus(Map<String, Object> map);

	// delete


//	List<Map<String, Object>> selectAll(Map<String, Object> map);
//	List<Map<String, Object>> searchProductCode(Map<String, Object> map);
//	List<Map<String, Object>> searchRequestDate(Map<String, Object> map);
//	List<Map<String, Object>> searchRequestDates(Map<String, Object> map);
//	List<Map<String, Object>> searchRequestStatus(Map<String, Object> map);

}
