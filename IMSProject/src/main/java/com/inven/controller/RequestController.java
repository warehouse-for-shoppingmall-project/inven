package com.inven.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.inven.common.CommonUtils;
import com.inven.common.Paging;
import com.inven.service.inter.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping(value = "/req/*")
public class RequestController {

    @Autowired
    RequestService reqService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView test(@RequestParam Map<String, Object> map) {
        log.info("Request Parameter : " + map);
        ModelAndView mv = new ModelAndView("/layout/write");

        List<Map<String, Object>> list = reqService.searchWhere(map);
        mv.addObject("list", list);

        return mv;
    }

    /*
     * mapping 주소가 /list 이면서 Method는 Get이고
     * request_fd안에 request_main.html로 가는 mv를 만들고
     * searchTitle의 값을 받아서 mv에 담아서 보내는 함수를 만들어봐
     * */
    // mappingurl이랑method이름을통일하면method이름이겹칠일이없다

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
        if (!map.containsKey("pageSize")) map.put("pageSize", "10");
        if (!map.containsKey("pageNo")) map.put("pageNo", "1");
        /* request_code product_code request_status pageSize start_date end_Date */
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam Map<String, Object> map) {
        log.info("Request Parameter : " + map);
        ModelAndView mv = new ModelAndView("/request_fd/request_main");
        List<Map<String, Object>> list = null;
        Paging paging = null;

        setFirstAccess(map);

        int count = reqService.searchCount(map);
        log.debug("count : " + count);
        if (count > 0) {
            paging = new Paging();
            int pageNo = Integer.parseInt(map.get("pageNo").toString());
            int pageSize = Integer.parseInt(map.get("pageSize").toString());
            paging.setPageNo(pageNo);
            paging.setTotalCount(count);
            paging.setPageSize(pageSize);

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
    public ModelAndView detail(@RequestParam Map<String, Object> map) {
        log.info("Request Parameter : " + map);
        ModelAndView mv = new ModelAndView("request_fd/request_detail");

        List<Map<String, Object>> detail = reqService.selectDetail(map.get("request_code").toString());
        mv.addObject("detail", detail);

        return mv;
    }

    @GetMapping(value = "/add")
    public ModelAndView add(@RequestParam Map<String, Object> map) {
        log.info("Request Parameter : " + map);
        ModelAndView mv = new ModelAndView("request_fd/request_add");
        List<String> list = reqService.selectProductCode();

        mv.addObject("list", list);

        return mv;
    }


    @GetMapping(value="/mod")
    public ModelAndView mod(@RequestParam Map<String, Object> map) {
        log.info("Request Parameter : " + map);
        ModelAndView mv = new ModelAndView("request_fd/request_modify");

        Map<String, Object> title = reqService.reqModifyTitle(map);
        List<Map<String, Object>> details = reqService.reqModifyDetail(map);
        List<String> list = reqService.selectProductCode();     //상품코드 select용

        log.info("Response modSelectSQL title : " + title);
        log.info("Response modSelectSQL details : " + details);

        mv.addObject("title",title);
        mv.addObject("details",details);
        mv.addObject("list",list);


        return mv;
    }

}
