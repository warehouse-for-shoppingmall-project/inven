package com.inven.service.inter;

import com.google.gson.Gson;
import com.inven.common.model.ProductDetail;
import com.inven.common.model.ProductTitle;
import com.inven.common.model.SearchParam;
import com.inven.param.ProductInformation;

import java.util.List;
import java.util.Map;

public interface ProductService {

//    Select

    int searchCount(SearchParam searchParam);

    List<ProductTitle> getTitleAll();


    List<ProductDetail> getDetailAll();


    List<ProductDetail> selectDetail(String product_code);


    List<ProductInformation> modify(String productCode);


    int overlapCheck(Map<String, Object> map);


    List<ProductTitle> search2(SearchParam searchParam);


    //    Insert

    int productTitlesAdd(ProductTitle productTitle);


    void productDetailsAdd(ProductDetail productDetail);


    int addProductData(Map<String, Object> map);

    //    Update

    void upStatus(ProductTitle productTitle);
}

