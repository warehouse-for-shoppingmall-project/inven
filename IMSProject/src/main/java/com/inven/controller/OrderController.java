package com.inven.controller;

import com.inven.common.Paging;
import com.inven.common.model.OrderManage;
import com.inven.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping(value = "/order/*")
public class OrderController {

    @Autowired
    OrderServiceImpl orderService;

    /************* Select **************/
    @GetMapping("/list")
    public ModelAndView list(@ModelAttribute("orderManage") OrderManage orderManage) {
        ModelAndView mv = new ModelAndView("order_fd/order_main");

        log.debug(ToStringBuilder.reflectionToString(orderManage));

        List<OrderManage> orderList = null;

        orderList = orderService.selectOrderManage();
        mv.addObject("orderManage", orderList);

        return mv; //-> product_main 이라는 html으로 리턴해라

        /* 페이징은 잠시 보류...
        Paging paging = null;
        int count = orderService.searchCount(orderManage);

        log.debug("count : " + count);

        if(count > 0){
            paging = new Paging();
            paging.setPageNo(searchParam.getPageNo());
            paging.setPageSize(searchParam.getPageSize());
            paging.setTotalCount(count);

            searchParam.setStart_idx(paging.getStartIndex());
            searchParam.setEnd_idx(paging.getPageSize());

            productTitles = productService.searchSelect(searchParam);
        }

        mv.addObject("paging", paging);
        */

    }

    /************* Insert **************/

    /************* Update **************/



}
