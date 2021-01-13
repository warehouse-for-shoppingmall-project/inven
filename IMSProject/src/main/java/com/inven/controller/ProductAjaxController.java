package com.inven.controller;

import com.inven.common.model.ProductTitle;
import com.inven.param.ProductInformation;
import com.inven.service.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@SuppressWarnings("unchecked")
@Slf4j
@RequestMapping(value = "/prod/async/*")
@RestController
public class ProductAjaxController {

	@Autowired
	ProductServiceImpl prodService;

    @PutMapping("/upStatus")
    public boolean upStatus(@RequestBody ProductInformation productInformation) {

        ProductTitle productTitle = new ProductTitle(productInformation.getProduct_code(),
                productInformation.getProduct_status());

        prodService.upStatus(productTitle);

        return true;
    }

	@GetMapping("/overlapCheck")
	public JSONObject overlapCheck(@RequestParam String product_code) {
		log.debug("Request Param : " + product_code);
		JSONObject jobj = new JSONObject();

		int rs = prodService.overlapCheck(product_code);

		jobj.put("code", rs == 0 ? 200 : 400);
		return jobj;
	}

    @PostMapping(value = "/prodAdd")
    public JSONObject prodAdd(@RequestParam Map<String, Object> map) {

        log.info("Request Parameter : " + map);
        JSONObject jobj = new JSONObject();
        jobj.put("code", 999);
        if (map == null) return jobj;

        int addResult = prodService.addProductData(map);

        if (addResult > 0) jobj.put("code", 200);
        else if (addResult == 0) jobj.put("code", 300);
        else if (addResult == -1) jobj.put("code", 400);

        return jobj;
    }

    @PostMapping(value = "/prodMod")
    public JSONObject prodMod(@RequestParam Map<String, Object> map) {
        log.info("Request Parameter : " + map);
        JSONObject jobj = new JSONObject();
        jobj.put("code", 999);
        if (map == null) return jobj;

        int rs = prodService.updateColumn(map);

        jobj.put("code", rs > 0 ? 200 : 400);

        return jobj;
    }

}
