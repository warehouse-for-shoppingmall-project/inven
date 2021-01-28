package com.inven.mapper;

import com.inven.common.model.OrderManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {


	/************* Select **************/

	@Select("select * from shop_db.order_manage")
	List<OrderManage> selectOrderManage();

	/************* Insert **************/

	/************* Update **************/

}
