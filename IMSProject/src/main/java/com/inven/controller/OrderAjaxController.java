package com.inven.controller;

import com.inven.common.model.OrderManage;
import com.inven.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("unchecked")
@Slf4j
@RequestMapping(value = "/order/async/*")
@RestController
public class OrderAjaxController {

	@Autowired
    OrderServiceImpl orderService;


    /************* Select **************/

    /************* Insert **************/

    /************* Update **************/


}
