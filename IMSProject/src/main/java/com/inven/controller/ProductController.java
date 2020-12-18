package com.inven.controller;

import java.util.List;
import java.util.Map;


import com.inven.common.CommonUtils;
import com.inven.common.model.ProductTitle;
import com.inven.mapper.ProductMapper;
import com.inven.service.inter.ProductService;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping(value = "/prod/*")
public class ProductController {

	@Autowired   // 굳이 ProductServiceImpl 하지말고 ProductService만 생성해주면된다.
	ProductService productService;

	@Autowired
	ProductMapper productMapper;

	// 1. localhost:8080/search?key1=value1 -> RequestParam
	//  map.get("key1")
//	@GetMapping(value = "/search")
//	public ModelAndView search(@RequestParam Map<String, Object> map) {
//		log.debug("Request Parameter : " + map);
//		ModelAndView mv = new ModelAndView("/product_fd/product_main");
//
//		List<Map<String, Object>> list = productService.search(map);
//
//		for(Map<String, Object> ent : list){
//			CommonUtils.printMap(ent);
//		}
//		mv.addObject("list", list);
//
//		return mv;
//	}
	@GetMapping("/list")
	public String list(Model model) {
		List<ProductTitle> product_titles = productService.printProduct();
		System.out.println(product_titles);
		model.addAttribute("productList", product_titles);
		return "/product_fd/product_main"; //-> product_main 이라는 html으로 리턴해라
	}

	// localhost:8080/search?where=code or date &query=박성수
	@GetMapping("/search")
	public String search(@RequestParam String where, @RequestParam String query, Model model) {
		List<ProductTitle> productTitles = null;
		if (where == "productCode") {
			productTitles = productService.searchByProductCode(query);
		} else if (where == "date") {
			productTitles = productService.searchByDate(query);
		}
		model.addAttribute("productTitles", productTitles);
		return "product_fd/product_main";
	}

//		final List<ProductTitle> search = productService.search(where, query);
//	@GetMapping("/search/{productCode}")
//	public ProductTitle getProductTitle (@PathVariable("product_code") String query) {
//		return productMapper.getProductTitle(query);
//	}


//	@RequestMapping(value = "/list" , method = RequestMethod.GET)
//	public ModelAndView list(@RequestParam Map<String, Object> map) {
//		log.debug("Request Parameter : " + map);
//		ModelAndView mv = new ModelAndView("/product_fd/product_main");
////		ModelAndView mv = new ModelAndView("/layout/write");
//
//		List<Map<String, Object>> list = prodService.selectAll(map);
//
//		mv.addObject("list", list);
//
//		return mv;
//	}




}
