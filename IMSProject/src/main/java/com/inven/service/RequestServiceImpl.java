package com.inven.service;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inven.mapper.RequestMapper;
import com.inven.service.inter.RequestService;


@Service("requestService")
public class RequestServiceImpl implements RequestService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RequestMapper reqMapper;

    // searchTitle함수를 반환하는 함수를 만들어봐 잘했어
    // Controller Method
    public int searchCount(Map<String, Object> map) {
        return reqMapper.searchCount(map);
    }

    public List<Map<String, Object>> searchDetail(Map<String, Object> map) {
        return reqMapper.searchDetail(map);
    }

    // Ajax Method
    public int upStatus(Map<String, Object> map) {
        return reqMapper.upStatus(map);
    }

    public List<Map<String, Object>> searchWhere(Map<String, Object> map) {
        return reqMapper.searchWhere(map);
    }


//	public List<Map<String, Object>> selectAll(Map<String, Object> map){ return reqMapper.selectAll(map); }
//	public List<Map<String, Object>> searchProductCode(Map<String, Object> map){ return reqMapper.searchProductCode(map); }
//	public List<Map<String, Object>> searchRequestDate(Map<String, Object> map){ return reqMapper.searchRequestDate(map); }
//	public List<Map<String, Object>> searchRequestDates(Map<String, Object> map){ return reqMapper.searchRequestDates(map); }
//	public List<Map<String, Object>> searchRequestStatus(Map<String, Object> map){ return reqMapper.searchRequestStatus(map); }

}
