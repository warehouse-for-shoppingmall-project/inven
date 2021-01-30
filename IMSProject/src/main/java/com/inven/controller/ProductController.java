package com.inven.controller;

import com.inven.common.Paging;
import com.inven.common.model.ProductDetail;
import com.inven.common.model.ProductTitle;
import com.inven.common.model.SearchParam;
import com.inven.service.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping(value = "/prod/*")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

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

            productTitles = productService.searchSelect(searchParam);
        }

        mv.addObject("paging", paging);
        mv.addObject("productTitles", productTitles);

        return mv; //-> product_main 이라는 html으로 리턴해라
    }

    @GetMapping("/add")
    public String add(@RequestParam Map<String, Object> map) {
        return "product_fd/product_add";
    }

    @GetMapping(value = "/detail")
    public ModelAndView detail(@RequestParam String code) {

        ModelAndView mv = new ModelAndView("detail_view");

        List<ProductDetail> details = productService.selectDetail(code);
        mv.addObject("details", details);

        return mv;
    }

    @GetMapping("/modify")
    public ModelAndView modify(@RequestParam String productCode) {
        ModelAndView mv = new ModelAndView("product_fd/product_modify");

        List<ProductDetail> details = productService.selectDetail(productCode);

        log.info("상품수정 detail 조회결과 : " + details);

        mv.addObject("details", details);
        mv.addObject("product_code", productCode);

        return mv;
    }

}
