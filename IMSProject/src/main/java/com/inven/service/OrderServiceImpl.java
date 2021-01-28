package com.inven.service;

import com.inven.common.model.OrderManage;
import com.inven.mapper.OrderMapper;
import com.inven.service.inter.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /************* Select **************/
    public List<OrderManage> selectOrderManage() { return orderMapper.selectOrderManage(); }

    /************* Insert **************/

    /************* Update **************/



}
