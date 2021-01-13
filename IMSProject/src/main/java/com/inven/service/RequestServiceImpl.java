package com.inven.service;

import java.util.*;

import com.google.gson.Gson;
import com.inven.common.CommonUtils;
import com.inven.mapper.RequestMapper;
import com.inven.service.inter.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service("requestService")
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestMapper reqMapper;

    // select
    public int searchCount(Map<String, Object> map){ return reqMapper.searchCount(map); }
    public List<Map<String, Object>> selectReqDetail(String request_code){ return reqMapper.selectReqDetail(request_code); }
    public List<Map<String, Object>> selectProdDetail(String product_code){ return reqMapper.selectProdDetail(product_code); }
    public Map<String, Object> selectProdTitle(String product_code){ return reqMapper.selectProdTitle(product_code); }
    public List<Map<String, Object>> searchWhere(Map<String, Object> map){ return reqMapper.searchWhere(map); }
    public List<Map<String, Object>> getAllProdCd(){ return reqMapper.getAllProdCd(); }
    public String makeReqCode(){ return reqMapper.makeReqCode(); }
    public Map<String, Object> reqModifyTitle(Map<String, Object> map){ return reqMapper.reqModifyTitle(map); }
    public List<Map<String, Object>> reqModifyDetail(Map<String, Object> map){ return reqMapper.reqModifyDetail(map); }

    // insert
    @Transactional()
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

    @Transactional()
    public int modRequestData(Map<String, Object> map){
        Gson gson = new Gson();

        Map<String, Object> title = gson.fromJson(map.get("title").toString(), Map.class);
        CommonUtils.printMap(title);

        List<Map<String, Object>> details = gson.fromJson(map.get("details").toString(), List.class);
        CommonUtils.printList(details);

        int detailRs = -1;
        try {
            int titleRs = reqMapper.modTitle(title);
            if(titleRs > 0) detailRs = reqMapper.modDetail(details);
        }catch (Exception e){
            detailRs = -2;
            e.printStackTrace();
        }

        return detailRs;
    }

    // delete

}
