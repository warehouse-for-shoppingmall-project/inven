package com.inven.service;

import com.google.gson.Gson;
import com.inven.common.model.ProductDetail;
import com.inven.common.model.ProductTitle;
import com.inven.common.model.SearchParam;
import com.inven.mapper.ProductMapper;
import com.inven.param.ProductInformation;
import com.inven.service.inter.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper prodMapper;

//    Select

    @Override
    public int searchCount(SearchParam searchParam) {
        return prodMapper.searchCount(searchParam);
    }

    @Override
    public List<ProductTitle> getTitleAll() {
        return prodMapper.getTitleAll();
    }

    @Override
    public List<ProductDetail> getDetailAll() {
        return prodMapper.getDetailAll();
    }

    @Override
    public List<ProductDetail> selectDetail(String product_code) { return prodMapper.selectDetail(product_code); }


    @Override
    public List<ProductInformation> modify(String productCode) {
        return prodMapper.modify(productCode);
    }

    @Override
    public int overlapCheck(Map<String, Object> map) {
        return prodMapper.overlapCheck(map);
    }


    public List<ProductTitle> search2(SearchParam searchParam) {
        return prodMapper.search2(searchParam);
    }


//    Insert

	@Override
	public int productTitlesAdd(ProductTitle productTitle){
		return prodMapper.productTitlesAdd(productTitle);
	}

    @Override
    public void productDetailsAdd(ProductDetail productDetail) {
		prodMapper.productDetailsAdd(productDetail);
    }

    @Override
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


//    Update
    @Override
    public void upStatus(ProductTitle productTitle) {
        prodMapper.upStatus(productTitle);
    }


}
