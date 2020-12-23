package com.inven.controller;

import java.util.List;
import java.util.Map;

import com.inven.common.model.ProductDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.inven.common.CommonUtils;
import com.inven.common.model.ProductTitle;
import com.inven.mapper.ProductMapper;
import com.inven.param.ProductInformation;
import com.inven.service.inter.ProductService;


@Controller
@RequestMapping(value = "/prod/*")
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired   // 굳이 ProductServiceImpl 하지말고 ProductService만 생성해주면된다.
    ProductService productService;

    @Autowired
    ProductMapper productMapper;

    // 1. localhost:8080/search?key1=value1 -> RequestParam
    //  map.get("key1")
    @GetMapping("/search")
    public ModelAndView search(@RequestParam Map<String, Object> map) {
        log.debug("Request Parameter : " + map);
        ModelAndView mv = new ModelAndView("/product_fd/product_main");

        List<Map<String, Object>> list = productService.search(map);

        for (Map<String, Object> ent : list) {
            CommonUtils.printMap(ent);
        }
        mv.addObject("list", list);

        return mv;
    }

    @GetMapping("/list")
    public ModelAndView list(@RequestParam Map<String, Object> map) {

        ModelAndView mv = new ModelAndView("product_fd/product_main");
        List<ProductTitle> product_titles = productService.printProduct();
        List<Map<String, Object>> productDetails = productService.detail(map);

        if (!map.containsKey("searchType")) map.put("searchType", "");
        if (!map.containsKey("searchText")) map.put("searchText", "");

        List<Map<String, Object>> search = productService.search(map);

        System.out.println(product_titles);

        log.info(String.valueOf(productDetails));
        log.info(String.valueOf(product_titles));
        mv.addObject("productDetail", productDetails);
        mv.addObject("productList", product_titles);
        mv.addObject("search", search);

        return mv; //-> product_main 이라는 html으로 리턴해라
    }

    @GetMapping("/add")
    public ModelAndView add(@RequestParam Map<String, Object> map) {
        log.info("Request Parameter : " + map);
        ModelAndView mv = new ModelAndView("product_fd/product_add");
//		List<String> productAdd = productService.productAdd();

//		mv.addObject("list",list);

        return mv;

    }


    // html form submit
    // form: application/x-www-form-url-encoded
    // 1. @RequestBody MultiValueMap
    // 2. @ModelAttribute ProductInformationParam

    // html form --> ajax
    // form: application/json
    // @RequestBody Map
    // @RequestBody ProductInformationParam

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)// Post는 RequestParam 못받는다
    public ModelAndView addProduct(@ModelAttribute ProductInformation productInformation) {
        ModelAndView mv = new ModelAndView();
        // ProductInformation --> ProductTitle

        ProductTitle productTitle = new ProductTitle(productInformation.getProduct_code(),
                productInformation.getMake_factory(),
                productInformation.getUnit_price(),
                productInformation.getFinal_update(),
                productInformation.getMake_code());

        productService.productTitlesAdd(productTitle);


        // ProductInformation --> ProductDetail
        ProductDetail productDetail = new ProductDetail(productInformation.getProduct_code(),
                productInformation.getColor_name(),
                productInformation.getGender(),
                productInformation.getS(),
                productInformation.getM(),
                productInformation.getL(),
                productInformation.getXl(),
                productInformation.getF(),
                productInformation.getTotal(),
                productInformation.getManufacture_day());

        productService.productDetailsAdd(productDetail);


        mv.setViewName("redirect:/prod/list");

        return mv;
    }


    // localhost:8080/search?where=code or date &query=박성수
//	@GetMapping("/search")
//	public String search(@RequestParam String where, @RequestParam String query, Model model) {
//		List<ProductTitle> productTitles = null;
//		if (where == "productCode") {
//			productTitles = productService.searchByProductCode(query);
//		} else if (where == "date") {
//			productTitles = productService.searchByDate(query);
//		}
//		model.addAttribute("productTitles", productTitles);
//		return "product_fd/product_main";
//	}

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
