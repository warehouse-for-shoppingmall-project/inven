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
	List<Map<String, Object>> searchWhere(Map<String, Object> map);
	List<Map<String, Object>> selectProdDetail(String product_code);
	List<Map<String, Object>> selectReqDetail(String request_code);
	List<Map<String, Object>> getAllProdCd();
	String makeReqCode();
	Map<String, Object> selectProdTitle(String product_code);
	Map<String, Object> reqModifyTitle(Map<String, Object> map);
	List<Map<String, Object>> reqModifyDetail(Map<String, Object> map);

	// insert
	int addTitle(Map<String, Object> map);
	int addDetail(List<Map<String, Object>> list);
	int modTitle(Map<String, Object> map);
	int modDetail(List<Map<String, Object>> list) ;

	// update
	int upStatus(Map<String, Object> map);



    // delete
}
