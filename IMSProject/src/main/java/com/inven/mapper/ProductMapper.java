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
@Repository  // DB접근하는 애들은 Repository 붙여준다. 이러면 'Bean'생성
public interface ProductMapper {

	//    Select
	int addTitle(Map<String, Object> title);

	int addDetails(List<String> details);

	@Select("Select * from product_title")
	List<ProductTitle> getTitleAll();

	@Select("Select * from product_detail")
	List<ProductDetail> getDetailAll();

	int searchCount(SearchParam searchParam);



	List<ProductTitle> getProductList();

	@Select("Select * from product_detail")
	List<ProductDetail> getDetail();

	List<ProductDetail> selectDetail(String product_code);
	List<ProductDetail> modReadDetail(String product_code);


	int overlapCheck(Map<String, Object> map);


	List<ProductTitle> search2(SearchParam searchParam);


	//    Insert

	int productTitlesAdd(ProductTitle productTitle);

	void productDetailsAdd(ProductDetail productDetail);

	int addColor(List<Map<String, Object>> list);

	//    Update

	void upStatus(ProductTitle productTitle);

	int modColor(List<Map<String, Object>> list);
}
