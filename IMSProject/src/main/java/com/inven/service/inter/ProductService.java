package com.inven.service.inter;

import com.inven.common.model.ProductTitle;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<ProductTitle> printProduct();

    List<ProductTitle> searchByProductCode(String query);

    List<ProductTitle> searchByDate(String query);

//    List<Map<String, Object>> selectAll(Map<String, Object> map);

//    List<ProductTitle> search(String where, String query);
}
