package com.inven.controller;

import java.util.List;
import java.util.Map;

import com.inven.common.model.ProductDetail;
import com.inven.param.ProductModifiedInformation;
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

    @GetMapping("/list")
    public ModelAndView list(@RequestParam Map<String, Object> map) {

        ModelAndView mv = new ModelAndView("product_fd/product_main");
        List<ProductTitle> productTitles = productService.printProduct();
        List<ProductDetail> productDetails = productService.getDetail();

        if (!map.containsKey("searchType")) map.put("searchType", "");
        if (!map.containsKey("searchText")) map.put("searchText", "");

//        List<Map<String, Object>> search = productService.search(map);

        System.out.println(productTitles);

        log.info(String.valueOf(productDetails));
        log.info(String.valueOf(productTitles));
        mv.addObject("productDetail", productDetails);
        mv.addObject("productTitles", productTitles);
//        mv.addObject("search", search);

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
                productInformation.getMake_code(),
                productInformation.getProduct_status());

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
    @GetMapping("/modify")
    public ModelAndView modify(@RequestParam String productCode) {
        //1. title , detail 가져오기 : productCode이용 -> mapper에서 select로 일단 가져오자
        List<ProductInformation> productInformations =  productService.modify(productCode);
        System.out.println(productInformations);


    //2. html에 보내주기 -> mv.setViewName("product_fd/productModify")
    ModelAndView mv = new ModelAndView();
        mv.setViewName("product_fd/productModify");
        mv.addObject("productInformation",productInformations);
    //3. html에서 타임리프이용 가져온 데이터를 띄우기 -> 수정가능 상태로 두기

        return mv;
}

    // localhost:8080/prod/search?where=productCode&query=CT001
    // localhost:8080/prod/search?where=date&query=2020-12-12
    @GetMapping("/search")
    public ModelAndView search(@RequestParam String where, @RequestParam String query) {
        if (query == null || query.equals("")) {
            final ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("redirect:/prod/list");
            return modelAndView;
        }

        log.info("where: {}, query: {}", where, query);
        final List<ProductTitle> productTitles = productService.search(where, query);
        log.info("productTitle: {}", productTitles);

        // where --> code
        // query --> 박성수
        // where --> code / date

        final ModelAndView mv = new ModelAndView();
        mv.setViewName("product_fd/product_main");
        mv.addObject("productTitles", productTitles);
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
