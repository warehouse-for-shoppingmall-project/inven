package com.inven.controller;

import com.inven.common.model.OrderManage;
import com.inven.common.model.ProductTitle;
import com.inven.param.OrderParameter;
import com.inven.param.ProductInformation;
import com.inven.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
@Slf4j
@RequestMapping(value = "/order/async/*")
@RestController
public class OrderAjaxController {

	@Autowired
    OrderServiceImpl orderService;


    /************* Insert **************/
    @PostMapping(value = "/makeTrackingNumber")
    @ResponseBody
    public JSONObject makeTrackingNumber(@RequestBody int orderNo) {
        log.info("송장번호 생성 버튼 클릭");

        JSONObject jobj = new JSONObject();
        jobj.put("code", 400);
        log.info("orderNo > " + orderNo);
        String deliveryNum= orderService.makeTrackingNumber(orderNo);
        if (deliveryNum != null) {
            jobj.put("code", 200);
            jobj.put("deliveryNum", deliveryNum);
        }

        return jobj;
    }

    /************* Update **************/
    @PutMapping("/upStatus")
    public boolean upStatus(@RequestBody OrderParameter orderParameter) {

        OrderManage orderManage = new OrderManage(orderParameter.getOrderNo(), orderParameter.getOrderStatus());

        orderService.upStatus(orderManage);

        return true;
    }

}
