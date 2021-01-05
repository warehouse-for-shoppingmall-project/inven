package com.inven.controller;

import com.inven.common.CommonUtils;
import com.inven.common.Paging;
import com.inven.common.model.ProductDetail;
import com.inven.common.model.ProductTitle;
import com.inven.common.model.SearchParam;
import com.inven.param.ProductInformation;
import com.inven.service.inter.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping(value = "/prod/*")
public class ProductController {

    @Autowired
    ProductService productService;

//    private void setFirstAccess(Map<String, Object> map){
//        Date date = new Date();
//        // sql 인자로 보낼 값
//        if (!map.containsKey("startDate")) map.put("startDate", (date.getYear() + 1900) + "-01-01");
//        if (!map.containsKey("endDate")) map.put("endDate", (date.getYear() + 1900) + "-12-31");
//        if (!map.containsKey("product_code")) map.put("product_code", "");
//        if (!map.containsKey("product_status")) map.put("product_status", "");
//        if (!map.containsKey("gender")) map.put("gender", "");
//
//        // paging 에서 쓸 값
//        if (!map.containsKey("pageSize")) map.put("pageSize", "10");
//        if (!map.containsKey("pageNo")) map.put("pageNo", "1");
//    }

    @GetMapping("/list")
    public ModelAndView list(@ModelAttribute("searchParams") SearchParam searchParam) {
        ModelAndView mv = new ModelAndView("product_fd/product_main");
        log.debug(ToStringBuilder.reflectionToString(searchParam));
        List<ProductTitle> productTitles = null;
        Paging paging = null;
        int count = productService.searchCount(searchParam);
        log.debug("count : " + count);
        if(count > 0){
            paging = new Paging();
            paging.setPageNo(searchParam.getPageNo());
            paging.setPageSize(searchParam.getPageSize());
            paging.setTotalCount(count);

            searchParam.setStart_idx(paging.getStartIndex());
            searchParam.setEnd_idx(paging.getPageSize());

            productTitles = productService.search2(searchParam);
        }

        mv.addObject("paging", paging);
        mv.addObject("productTitles", productTitles);

        return mv; //-> product_main 이라는 html으로 리턴해라
    }

    @GetMapping("/add")
    public ModelAndView add(@RequestParam Map<String, Object> map) {
        log.info("Request Parameter : " + map);
        ModelAndView mv = new ModelAndView("product_fd/product_add");

        return mv;

    }

    @GetMapping(value = "/detail")
    public ModelAndView detail(@RequestParam String product_code) {

        ModelAndView mv = new ModelAndView("product_fd/productDetail");

        List<ProductDetail> detail = productService.selectDetail(product_code);
        mv.addObject("detail", detail);

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
    public ModelAndView addProduct(@RequestParam Map<String, Object> map) {
        log.info("Request Parameter : " + map);
        ModelAndView mv = new ModelAndView();
        // ProductInformation --> ProductTitle
        log.debug("param : " + map);

        CommonUtils.printMap(map);

//        productService.productTitlesAdd(productTitle);

//        productService.productDetailsAdd(productDetail);

        mv.setViewName("redirect:/prod/list");

        return mv;
    }

    @GetMapping("/modify")
    public ModelAndView modify(@RequestParam String productCode) {
        ModelAndView mv = new ModelAndView("product_fd/productModify");

        //1. title , detail 가져오기 : productCode이용 -> mapper에서 select로 일단 가져오자
//        List<List<ProductInformation>> productModRead = null;
        List<ProductDetail> detail = productService.modReadDetail(productCode);

        log.info("상품수정 detail 조회결과 : " + detail);

//        if (productModRead.isEmpty()) {
//            mv.setViewName("redirect:/prod/list");
//        }

        mv.addObject("detail",detail);
        mv.addObject("product_code", productCode);

//        ProductTitle productTitle = new ProductTitle(productModRead.get(0));
//        log.info("상품 infomations의 첫번째 값 : " + productModRead.get(0));

        // List<ProductInformation> --> List<ProductDetail>
        // ProductInformation --> ProductDetail
//        List<ProductDetail> productDetailList = new ArrayList<>();
//        for (ProductInformation productInformation : productModRead) {
//            // ProductInformation --> ProductDetail
//            ProductDetail productDetail = new ProductDetail(productInformation);
//            productDetailList.add(productDetail);
//        }
//
//        System.out.println(productTitle);
//        System.out.println(productDetailList);
//
//        //2. html에 보내주기 -> mv.setViewName("product_fd/productModify")
//        mv.setViewName("product_fd/productModify");
//        mv.addObject("productTitle", productTitle);
//        mv.addObject("productDetailList", productDetailList);
        //3. html에서 타임리프이용 가져온 데이터를 띄우기 -> 수정가능 상태로 두기

        return mv;
    }

    @GetMapping("/search2")
    public ModelAndView search(@ModelAttribute("searchParams") SearchParam searchParam) {
        ModelAndView mv = new ModelAndView("product_fd/product_main");
        log.info(String.valueOf(searchParam));
        log.debug(ToStringBuilder.reflectionToString(searchParam));
        List<ProductTitle> productTitles = productService.search2(searchParam);
        mv.addObject("productTitles",productTitles);
        return mv;
    }

    // localhost:8080/prod/search?where=productCode&query=CT001
    // localhost:8080/prod/search?where=date&query=2020-12-12
//    @GetMapping("/search")
//    public ModelAndView search(@RequestParam String where, @RequestParam String query ,@RequestParam(required = false) String query1){
//        if (query == null || query.equals("")) {
//            final ModelAndView modelAndView = new ModelAndView();
//            modelAndView.setViewName("redirect:/prod/list");
//            return modelAndView;
//        }
//
//        log.info("where: {}, query: {}", where, query);
//        final List<ProductTitle> productTitles = productService.search(where, query, query1);
//        log.info("productTitle: {}", productTitles);
//
//        // where --> code
//        // query --> 박성수
//        // where --> code / date
//
//        final ModelAndView mv = new ModelAndView();
//        mv.setViewName("product_fd/product_main");
//        mv.addObject("productTitles", productTitles);
//        return mv;
//    }


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
