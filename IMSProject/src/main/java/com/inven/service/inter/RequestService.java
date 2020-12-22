package com.inven.service.inter;

import java.util.List;
import java.util.Map;

public interface RequestService {
    // Controller Method
    int searchCount(Map<String, Object> map);
    List<Map<String, Object>> searchWhere(Map<String, Object> map);
    List<String> selectProductCode();


    // Ajax Method+
    List<Map<String, Object>> addTitle(Map<String, Object> map);
    List<Map<String, Object>> addDetail(Map<String, Object> map);
    int upStatus(Map<String, Object> map);
    String makeReqCode();
}
