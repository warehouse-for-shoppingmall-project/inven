package com.inven.service;

import java.util.*;

import com.inven.common.model.ProductDetail;
import com.inven.common.model.ProductTitle;
import com.inven.param.ProductInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inven.mapper.ProductMapper;
import com.inven.service.inter.ProductService;

@Slf4j
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper prodMapper;

    @Override
    public List<ProductTitle> printProduct() {
        return prodMapper.getProductList();
    }

    public List<ProductDetail> getDetail() {
        return prodMapper.getDetail();
    }


//    @Override
//    public List<String> productAdd() {
//        return prodMapper.productAdd();
//    }

	@Override
	public int productTitlesAdd(ProductTitle productTitle){
		return prodMapper.productTitlesAdd(productTitle);
	}

    @Override
    public void productDetailsAdd(ProductDetail productDetail) {
		prodMapper.productDetailsAdd(productDetail);
    }

    @Override
    public List<ProductTitle> search(String where, String query) {
        if (where.equals("productCode")) {
            return prodMapper.searchByProductCode(query);
        } else if (where.equals("date")) {
            return prodMapper.searchByDate(query);
        }
        return null;
    }

    public void upStatus(ProductTitle productTitle) {
        prodMapper.upStatus(productTitle);
    }

    @Override
    public List<ProductInformation> modify(String productCode) {
        return prodMapper.modify(productCode);
    }

//	@Override
//	public List<ProductTitle> searchByProductCode(String query) {
//		List<ProductTitle> productTitles = prodMapper.searchByProductCode(query);
//		return productTitles;
//	}
//
//	@Override
//	public List<ProductTitle> searchByDate(String query) {
//		List<ProductTitle> productTitles = prodMapper.searchByDate(query);
//		return productTitles;
//	}
//	@Override
//	public List<ProductTitle> search(String where, String query) {
//		List<ProductTitle> search = prodMapper.search(where, query);
//		return  search;
//	}


//	@Override
//	public List<Map<String, Object>> selectAll(Map<String, Object> map) {
//		return prodMapper.selectAll(map);
//	}

//    @Override
//    public List<Map<String, Object>> detail(Map<String, Object> map) {
//        return prodMapper.detail(map);
//    }


}
