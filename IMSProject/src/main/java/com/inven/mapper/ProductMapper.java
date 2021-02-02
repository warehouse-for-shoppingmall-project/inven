package com.inven.mapper;

import com.inven.common.model.ProductDetail;
import com.inven.common.model.ProductTitle;

import com.inven.common.model.SearchParam;
import com.inven.param.ProductInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ProductMapper {

	//    Select
	@Select("Select * from product_title")
	List<ProductTitle> getTitleAll();
	@Select("Select * from product_detail")
	List<ProductDetail> getDetailAll();
	@Select("Select * from product_detail")
	List<ProductDetail> getDetail();

	List<ProductDetail> selectDetail(String product_code);
	List<ProductTitle> searchSelect(SearchParam searchParam);

	int searchCount(SearchParam searchParam);
	int overlapCheck(String productCode);

	//    Insert
	int addTitle(Map<String, Object> title);
	int addDetails(List<Map<String, Object>> details);

	//    Update
	void upStatus(ProductTitle productTitle);
	int modColor(List<Map<String, Object>> list);
}
