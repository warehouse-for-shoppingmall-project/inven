package com.inven.service.inter;

import java.util.List;
import java.util.Map;

public interface RequestService {
    int searchCount(Map<String, Object> map);
    List<Map<String, Object>> searchDetail(Map<String, Object> map);
     List<Map<String, Object>> searchWhere(Map<String, Object> map);

    // Ajax Method
    int upStatus(Map<String, Object> map);
}
