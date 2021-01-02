package com.inven.service.inter;

import com.inven.common.model.ProductDetail;
import com.inven.common.model.ProductTitle;
import com.inven.param.ProductInformation;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<ProductTitle> getTitleAll();

    List<ProductDetail> getDetailAll();

    List<ProductDetail> selectDetail(String product_code);

    int productTitlesAdd(ProductTitle productTitle);

    void productDetailsAdd(ProductDetail productDetail);

    List<ProductTitle> search(String where, String query);


    void upStatus(ProductTitle productTitle);

    List<ProductInformation> modify(String productCode);

//    List<ProductTitle> searchByProductCode(String query);
//
//    List<ProductTitle> searchByDate(String query);

//    List<Map<String, Object>> selectAll(Map<String, Object> map);

//    List<Map<String, Object>> detail(Map<String, Object> map);

//    List<String> productAdd();


}

