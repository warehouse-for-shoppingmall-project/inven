package com.inven.mapper;

import com.inven.common.model.OrderManage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {


	/************* Select **************/

	List<OrderManage> searchOrder(OrderManage orderManage);
	int orderCount(OrderManage orderManage);

	/************* Insert **************/

	String makeTrackingNumber();

	/************* Update **************/

	void upStatus(OrderManage orderManage);


}
