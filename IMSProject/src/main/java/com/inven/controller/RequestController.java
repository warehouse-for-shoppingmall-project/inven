package com.inven.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.inven.common.Paging;
import com.inven.service.RequestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping(value = "/req/*")
public class RequestController {

    @Autowired
    RequestServiceImpl reqService;

    private void setFirstAccess(Map<String, Object> map){
        Date date = new Date();
        // sql 인자로 보낼 값
        if (!map.containsKey("start_date")) map.put("start_date", (date.getYear() + 1900) + "-01-01");
        if (!map.containsKey("end_date")) map.put("end_date", (date.getYear() + 1900) + "-12-31");
        if (!map.containsKey("request_code")) map.put("request_code", "");
        if (!map.containsKey("product_code")) map.put("product_code", "");
        if (!map.containsKey("request_status")) map.put("request_status", "");
        if (!map.containsKey("gender")) map.put("gender", "");

        // paging 에서 쓸 값
        if (!map.containsKey("pageSize")) map.put("pageSize", 10);
        if (!map.containsKey("pageNo")) map.put("pageNo", 1);
        /* request_code product_code request_status pageSize start_date end_Date */
    }

    @GetMapping("/list")
    public ModelAndView list(@RequestParam Map<String, Object> map) {
        ModelAndView mv = new ModelAndView("request_fd/request_main");
        List<Map<String, Object>> list = null;
        Paging paging = null;

        setFirstAccess(map);

        log.info("Request Parameter : " + map);

        int count = reqService.searchCount(map);
        log.debug("count : " + count);
        if (count > 0) {
            paging = new Paging();
            int pageNo = Integer.parseInt(map.get("pageNo").toString());
            int pageSize = Integer.parseInt(map.get("pageSize").toString());
            paging.setPageNo(pageNo);
            paging.setPageSize(pageSize);
            paging.setTotalCount(count);

            map.put("start_idx", paging.getStartIndex());
            map.put("end_idx", paging.getPageSize());

            list = reqService.searchWhere(map);
        }
        mv.addObject("list", list);
        mv.addObject("paging", paging);
        mv.addObject("map", map);

        return mv;
    }

    @GetMapping(value = "/detail")
    public ModelAndView detail(@RequestParam String code) {
        log.info("Request Parameter : " + code);
        ModelAndView mv = new ModelAndView("detail_view");

        List<Map<String, Object>> details = reqService.selectReqDetail(code);
        mv.addObject("details", details);
        return mv;
    }

    @GetMapping(value = "/add")
    public ModelAndView add(@RequestParam Map<String, Object> map) {
        log.info("Request Parameter : " + map);
        ModelAndView mv = new ModelAndView("request_fd/request_add");
        List<Map<String, Object>> list = reqService.getAllProdCd();
        Map<String, Object> prod = map.containsKey("product_code") ? reqService.selectProdTitle(map.get("product_code").toString()) : null;

        mv.addObject("prod", prod);
        mv.addObject("list", list);

        return mv;
    }


    @GetMapping(value="/mod")
    public ModelAndView mod(@RequestParam Map<String, Object> map) {
        log.info("Request Parameter : " + map);
        ModelAndView mv = new ModelAndView("request_fd/request_modify");

        Map<String, Object> title = reqService.reqModifyTitle(map);
        List<Map<String, Object>> details = reqService.reqModifyDetail(map);
        List<Map<String, Object>> list = reqService.getAllProdCd();     //상품코드 select용

        log.info("Response modSelectSQL title : " + title);
        log.info("Response modSelectSQL details : " + details);

        mv.addObject("title",title);
        mv.addObject("details",details);
        mv.addObject("list",list);


        return mv;
    }

}
