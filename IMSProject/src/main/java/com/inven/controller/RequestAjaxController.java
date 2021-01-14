package com.inven.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.inven.service.RequestServiceImpl;


@SuppressWarnings("unchecked")
@RequestMapping(value = "/req/async/*")
@RestController
public class RequestAjaxController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "requestService")
    RequestServiceImpl reqService;

    /* JSONObject Key => code
     * value 의미
     * 200 : Success
     * 400 : Error */
    @GetMapping(value = "selectProdDetail")
    public JSONObject selectDetail(@RequestParam Map<String, Object> map) {
        JSONObject jobj = new JSONObject();
        jobj.put("code", 400);
        List<Map<String, Object>> details = reqService.selectProdDetail(map.get("product_code").toString());
        if (details != null || details.size() > 0) {
            jobj.put("code", 200);
            jobj.put("details", details);
        }
        return jobj;
    }

    @PutMapping("/upStatus")
    public JSONObject upStatus(@RequestParam Map<String, Object> map) {
        log.info("Request Parameter : " + map);
        JSONObject jobj = new JSONObject();
        jobj.put("code", 400);

        int rs = 0;
        try {
            rs = reqService.upStatus(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rs > 0)
            jobj.put("code", 200);

        return jobj;
    }

    @GetMapping(value = "/makeReqCode")
    public JSONObject makeReqCode() {
        log.info("Request Parameter : 발주코드 생성 버튼 클릭");

        JSONObject jobj = new JSONObject();
        jobj.put("code", 400);

        String request_code = reqService.makeReqCode();
        if (request_code != null) {
            jobj.put("code", 200);
            jobj.put("request_code", request_code);
        }

        return jobj;
    }

    @PostMapping(value = "/reqAdd")
    public JSONObject reqAdd(@RequestParam Map<String, Object> map) throws SQLException {
        log.info("Request Parameter : " + map);
        JSONObject jobj = new JSONObject();
        jobj.put("code", 999);
        if (map == null) return jobj;

        int addResult = reqService.addRequestData(map);

        if (addResult > 0)
            jobj.put("code", 200);
        else if (addResult == 0)
            jobj.put("code", 300);
        else if (addResult == -1)
            jobj.put("code", 400);

        return jobj;
    }

    @PutMapping(value = "/reqMod")
    public JSONObject reqMod(@RequestParam Map<String, Object> map) {
        log.info("Request Parameter : " + map);
        JSONObject jobj = new JSONObject();
        jobj.put("code", 999);
        if (map == null) return jobj;

        int modResult = reqService.modRequestData(map);

        if (modResult > 0)
            jobj.put("code", 200);
        else if (modResult == 0)
            jobj.put("code", 300);
        else if (modResult == -1)
            jobj.put("code", 400);

        return jobj;
    }
}
