package com.inven.service;

import java.util.*;

import com.inven.common.model.ProductTitle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inven.mapper.ProductMapper;
import com.inven.service.inter.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductMapper prodMapper;


	@Override
	public List<ProductTitle> printProduct() {
		List<ProductTitle> product_titles = prodMapper.getProductList();
		return product_titles;
	}

	@Override
	public List<Map<String, Object>> detail(Map<String, Object> map) {
		return prodMapper.detail(map);
	}

	@Override
	public List<Map<String, Object>> search(Map<String, Object> map) {
		return prodMapper.search(map);
	}

	@Override
	public List<String> productAdd() {
		return prodMapper.productAdd();
	}

	@Override
	public int productTitlesAdd(ProductTitle productTitle){
		return prodMapper.productTitlesAdd(productTitle);
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




}
