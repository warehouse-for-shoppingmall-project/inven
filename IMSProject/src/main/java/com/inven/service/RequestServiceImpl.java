package com.inven.service;

import java.util.*;

import com.google.gson.Gson;
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
    public List<Map<String, Object>> selectReqDetail(String request_code){ return reqMapper.selectReqDetail(request_code); }
    public List<Map<String, Object>> selectProdDetail(String product_code){ return reqMapper.selectProdDetail(product_code); }
    public List<Map<String, Object>> searchWhere(Map<String, Object> map){ return reqMapper.searchWhere(map); }
    public List<String> getAllProdCd(){ return reqMapper.getAllProdCd(); }
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

    @Override
    public int addRequestData(Map<String, Object> map) {
        Gson gson = new Gson();

        Map<String, Object> title = gson.fromJson(map.get("title").toString(), Map.class);
        List<Map<String, Object>> details = gson.fromJson(map.get("details").toString(), List.class);

        int detailRs = -1;
        try {
            int titleRs = reqMapper.addTitle(title);
            if(titleRs > 0) detailRs = reqMapper.addDetail(details);
        }catch (Exception e){
            detailRs = -2;
            e.printStackTrace();
        }

        return detailRs;
    }
    // update
    public int upStatus(Map<String, Object> map) { return reqMapper.upStatus(map); }
    public int modTitle(Map<String, Object> map) {
        if(map.get("manufacturing_date").toString().equals(""))
            map.put("manufacturing_date", null);
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
