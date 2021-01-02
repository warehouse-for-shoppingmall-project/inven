package com.inven.service.inter;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface RequestService {
    // select
    int searchCount(Map<String, Object> map);
    List<Map<String, Object>> searchWhere(Map<String, Object> map);
    List<Map<String, Object>> selectReqDetail(String request_code);
    List<Map<String, Object>> selectProdDetail(String product_code);
    List<String> getAllProdCd();
    String makeReqCode();
    Map<String, Object> reqModifyTitle(Map<String, Object> map);
    List<Map<String, Object>> reqModifyDetail(Map<String, Object> map);

    // insert
    int addTitle(Map<String, Object> map);
    int addDetail(List<Map<String, Object>> list);
    int addRequestData(Map<String, Object> title) throws SQLException;

    // update
    int upStatus(Map<String, Object> map);
    int modTitle(Map<String, Object> map);
    int modDetail(List<Map<String, Object>> list);

    // delete
}
