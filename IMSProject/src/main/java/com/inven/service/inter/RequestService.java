package com.inven.service.inter;

import java.util.List;
import java.util.Map;

public interface RequestService {
    // select
    int searchCount(Map<String, Object> map);
    List<Map<String, Object>> searchDetail(Map<String, Object> map);
    List<Map<String, Object>> searchWhere(Map<String, Object> map);
    List<String> selectProductCode();
    String makeReqCode();
    Map<String, Object> reqModifyTitle(Map<String, Object> map);
    List<Map<String, Object>> reqModifyDetail(Map<String, Object> map);

    // insert
    int addTitle(Map<String, Object> map);
    int addDetail(List<Map<String, Object>> list);

    // update
    int upStatus(Map<String, Object> map);
    int modTitle(Map<String, Object> map);
    int modDetail(List<Map<String, Object>> list);


    // delete
}
