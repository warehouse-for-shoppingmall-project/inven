package com.inven.controller;

import com.inven.common.model.ProductTitle;
import com.inven.param.ProductInformation;
import com.inven.service.ProductServiceImpl;
import org.apache.coyote.Response;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/*
 * Class 에도 부모 annotation 설정 가능
 * ex) 여기에 RequestMapping(value="common")
 * 해두면 이 밑에 맵핑들은 다 common/___ 으로 접근해야함
 */

@SuppressWarnings("unchecked")
@RequestMapping(value = "/prod/async/*")
@RestController // <- Json형태로 반환     // Controller <- html을 반환
public class ProductAjaxController {

	@Resource(name="productService")
	ProductServiceImpl prodService = new ProductServiceImpl();


	//	json -> @RequestBody 로 받아야함
	//	x-www-urlencoded -> @RequestParam 로 받아야함
	//	RestController
	@PostMapping("/upStatus")
	public boolean upStatus(@RequestBody ProductInformation productInformation) {

		ProductTitle productTitle = new ProductTitle(productInformation.getProduct_code(),
				productInformation.getProduct_status());

		prodService.upStatus(productTitle);

		return true;
	}

	/* JSONObject Key => code
	 * value 의미
	 * 200 : Success
	 * 400 : Error */

//	@GetMapping("/prodTest.do")
////	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
//	public JSONObject test(@RequestParam Map<String, Object> map) {
//		log.info("Request Parameter : " + map);
//
//		JSONObject jobj = new JSONObject();
//		jobj.put("code", 400);
//
//		List<Map<String, Object>> list = prodService.selectAll(map);
//		if(list != null) {
//			jobj.put("code", 200);
//			jobj.put("list", list);
//		}
//
//		return jobj;
//	}


}
