package com.inven.service;

import java.util.*;

import com.google.gson.Gson;
import com.inven.common.model.ProductDetail;
import com.inven.common.model.ProductTitle;
import com.inven.param.ProductInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inven.mapper.ProductMapper;
import com.inven.service.inter.ProductService;

@Slf4j
@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper prodMapper;

    @Override
    public List<ProductTitle> getTitleAll() {
        return prodMapper.getTitleAll();
    }

    public List<ProductDetail> getDetailAll() {
        return prodMapper.getDetailAll();
    }

    public List<ProductDetail> selectDetail(String product_code) { return prodMapper.selectDetail(product_code); }


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

    public int overlapCheck(Map<String, Object> map) {
        return prodMapper.overlapCheck(map);
    }

    public int addProductData(Map<String, Object> map) {
        Gson gson = new Gson();

        Map<String, Object> title = gson.fromJson(map.get("title").toString(), Map.class);
        List<String> details = gson.fromJson(map.get("details").toString(), List.class);

        int detailRs = -1;
        try {
            int titleRs = prodMapper.addTitle(title);
            if(titleRs > 0) detailRs = prodMapper.addDetails(details);
        }catch (Exception e){
            detailRs = -2;
            e.printStackTrace();
        }

        return detailRs;
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
