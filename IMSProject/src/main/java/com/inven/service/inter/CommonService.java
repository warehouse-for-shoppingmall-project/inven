package com.inven.service.inter;

import java.io.IOException;
import java.util.Map;

public interface CommonService {
    boolean loginCheck(Map<String, Object> map) throws IOException;
    boolean loginChange(Map<String, Object> map) throws IOException;
}
