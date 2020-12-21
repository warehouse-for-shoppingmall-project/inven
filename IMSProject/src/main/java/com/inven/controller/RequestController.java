package com.inven.controller;

import java.util.List;
import java.util.Map;


import com.inven.common.Paging;
import com.inven.service.inter.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam Map<String, Object> map) {
        log.info("Request Parameter : " + map);
        ModelAndView mv = new ModelAndView("/request_fd/request_main");

        if (!map.containsKey("confirm")) map.put("confirm", "");
        if (!map.containsKey("value")) map.put("value", "");

        int count = reqService.searchCount(map);
        if (count < 1) mv.addObject("size", 0);
        else {
            Paging paging = new Paging();
            int pageNo = map.containsKey("pageNo") ? Integer.parseInt(map.get("pageNo").toString()) : 1;
            int pageSize = map.containsKey("pageSize") ? Integer.parseInt(map.get("pageSize").toString()) : 10;
            paging.setPageNo(pageNo);
            paging.setTotalCount(count);
            paging.setPageSize(pageSize);
            map.put("start_idx", paging.getStartIndex());
            map.put("end_idx", paging.getPageSize());

            List<Map<String, Object>> list = reqService.searchWhere(map);

            mv.addObject("list", list);
        }

        return mv;
    }

}
