package com.inven.mapper;

import java.util.List;
import java.util.Map;

import com.inven.common.model.ProductTitle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository  // DB접근하는 애들은 Repository 붙여준다. 이러면 'Bean'생성
public interface ProductMapper {


	@Select("SELECT * FROM product_title")
	List<ProductTitle> getProductList();

//	@Select("SELECT * FROM product_title where product_code = #{query}")
//	ProductTitle getProductTitle(@Param("product_code") String query);

	@Select("SELECT * FROM product_title where product_code = #{query} ")
	List<ProductTitle> searchByProductCode(String query);

	@Select("SELECT * FROM product_title where final_update = #{query}")
	List<ProductTitle> searchByDate(String query);


	List<Map<String, Object>> selectAll(Map<String, Object> map);



//	@Select("Select * from product_title where product")
//	List<ProductTitle> search(String where, String query);
}
