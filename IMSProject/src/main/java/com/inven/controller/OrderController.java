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
    public ModelAndView list(@ModelAttribute("orderManages") OrderManage orderManage) {
        ModelAndView mv = new ModelAndView("order_fd/order_main");

        log.debug(ToStringBuilder.reflectionToString(orderManage));

        List<OrderManage> orderList = null;
        Paging paging = null;

        int count = orderService.orderCount(orderManage);
        log.debug("count : " + count);
        if (count > 0) {
            paging = new Paging();

            paging.setPageNo(orderManage.getPageNo());
            paging.setPageSize(orderManage.getPageSize());
            paging.setTotalCount(count);

            orderManage.setStart_idx(paging.getStartIndex());
            orderManage.setEnd_idx(paging.getPageSize());

            orderList = orderService.searchOrder(orderManage);
        }
        mv.addObject("paging", paging);
        mv.addObject("orderList", orderList);

        return mv;
    }

    /************* Insert **************/

    /************* Update **************/



}
