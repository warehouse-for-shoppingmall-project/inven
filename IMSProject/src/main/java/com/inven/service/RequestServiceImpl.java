package com.inven.service;

import java.util.*;

import com.inven.mapper.RequestMapper;
import com.inven.service.inter.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("requestService")
public class RequestServiceImpl implements RequestService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RequestMapper reqMapper;


    // select
    public int searchCount(Map<String, Object> map){ return reqMapper.searchCount(map); }
    public List<Map<String, Object>> searchDetail(Map<String, Object> map){ return reqMapper.searchDetail(map); }
    public List<Map<String, Object>> searchWhere(Map<String, Object> map){ return reqMapper.searchWhere(map); }
    public List<String> selectProductCode(){ return reqMapper.selectProductCode(); }
    public String makeReqCode(){ return reqMapper.makeReqCode(); }
    public Map<String, Object> reqModifyTitle(Map<String, Object> map){ return reqMapper.reqModifyTitle(map); }
    public List<Map<String, Object>> reqModifyDetail(Map<String, Object> map){ return reqMapper.reqModifyDetail(map); }

    // insert
    public int addTitle(Map<String, Object> map){
        if(map.get("manufacturing_date").toString().equals(""))
            map.put("manufacturing_date", null);
        return reqMapper.addTitle(map);
    }
    public int addDetail(List<Map<String, Object>> list){ return reqMapper.addDetail(list); }

    // update
    public int upStatus(Map<String, Object> map) {return reqMapper.upStatus(map);}
    public int modTitle(Map<String, Object> map) {
        if(map.get("manufacturing_date").toString().equals(""))
            map.put("manufacturing_date", null);

        if(map.get("etc").toString().equals(""))
            map.put("etc","");

        return reqMapper.modTitle(map);
    }
    public int modDetail(List<Map<String, Object>> list) { return reqMapper.modDetail(list);}
    // delete


//	public List<Map<String, Object>> selectAll(Map<String, Object> map){ return reqMapper.selectAll(map); }
//	public List<Map<String, Object>> searchProductCode(Map<String, Object> map){ return reqMapper.searchProductCode(map); }
//	public List<Map<String, Object>> searchRequestDate(Map<String, Object> map){ return reqMapper.searchRequestDate(map); }
//	public List<Map<String, Object>> searchRequestDates(Map<String, Object> map){ return reqMapper.searchRequestDates(map); }
//	public List<Map<String, Object>> searchRequestStatus(Map<String, Object> map){ return reqMapper.searchRequestStatus(map); }

}
