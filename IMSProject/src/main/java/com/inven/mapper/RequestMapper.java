package com.inven.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RequestMapper {

	// 방금 만든 sql문을 접근하는 함수를 만들어봐

	int searchCount(Map<String, Object> map);
	List<Map<String, Object>> searchDetail(Map<String, Object> map);
	List<Map<String, Object>> searchWhere(Map<String, Object> map);
	List<String> selectProductCode();


	// Ajax Method+
	List<Map<String, Object>> addTitle(Map<String, Object> map);
	List<Map<String, Object>> addDetail(Map<String, Object> map);
	int upStatus(Map<String, Object> map);
	String makeReqCode();



//	List<Map<String, Object>> selectAll(Map<String, Object> map);
//	List<Map<String, Object>> searchProductCode(Map<String, Object> map);
//	List<Map<String, Object>> searchRequestDate(Map<String, Object> map);
//	List<Map<String, Object>> searchRequestDates(Map<String, Object> map);
//	List<Map<String, Object>> searchRequestStatus(Map<String, Object> map);

}
