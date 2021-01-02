package com.inven.mapper;

import com.inven.common.model.ProductDetail;
import com.inven.common.model.ProductTitle;

import com.inven.param.ProductInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository  // DB접근하는 애들은 Repository 붙여준다. 이러면 'Bean'생성
public interface ProductMapper {

	List<ProductDetail> selectDetail(String product_code);

	List<ProductTitle> searchByProductCode(String query);

	List<ProductTitle> searchByDate(String query);

	List<ProductInformation> modify(String productCode);

	//	Select
	@Select("Select * from product_title")
	List<ProductTitle> getTitleAll();

	@Select("Select * from product_detail")
	List<ProductDetail> getDetailAll();

    int overlapCheck(Map<String, Object> map);

	// Insert
	int productTitlesAdd(ProductTitle productTitle);
	void productDetailsAdd(ProductDetail productDetail);

	int addTitle(Map<String, Object> title);
	int addDetails(List<String> details);

	// Update
    void upStatus(ProductTitle productTitle);


	// Delete

//	@Select("SELECT * FROM product_title where product_code = #{query}")
//	ProductTitle getProductTitle(@Param("product_code") String query);

//	@Select("SELECT * FROM product_title where product_code = #{query} ")
//	List<ProductTitle> searchByProductCode(@Param("query") String query);
//

//	@Select("SELECT * FROM product_title where final_update = #{query}")

//	List<ProductTitle> searchByDate(String query);

//	List<String> productAdd();
//	List<Map<String, Object>> selectAll(Map<String, Object> map);




//	@Select("Select * from product_title where product")
//	List<ProductTitle> search(String where, String query);
}
