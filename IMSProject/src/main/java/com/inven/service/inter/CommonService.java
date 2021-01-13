package com.inven.service.inter;

import java.util.Map;

public interface CommonService {

    boolean loginCheck(Map<String, Object> map);
    boolean loginChange(Map<String, Object> map);

}
