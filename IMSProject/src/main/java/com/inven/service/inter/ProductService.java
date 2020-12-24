package com.inven.service.inter;

import com.inven.common.model.ProductDetail;
import com.inven.common.model.ProductTitle;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<ProductTitle> printProduct();

    List<ProductDetail> getDetail();

    int productTitlesAdd(ProductTitle productTitle);

    void productDetailsAdd(ProductDetail productDetail);

    List<ProductTitle> search(String where, String query);


    void upStatus(ProductTitle productTitle);

//    List<ProductTitle> searchByProductCode(String query);
//
//    List<ProductTitle> searchByDate(String query);

//    List<Map<String, Object>> selectAll(Map<String, Object> map);

//    List<Map<String, Object>> detail(Map<String, Object> map);

//    List<String> productAdd();


}

