package com.inven.controller;

import com.inven.common.model.ProductTitle;
import com.inven.param.ProductInformation;
import com.inven.service.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Map;


/*
 * Class 에도 부모 annotation 설정 가능
 * ex) 여기에 RequestMapping(value="common")
 * 해두면 이 밑에 맵핑들은 다 common/___ 으로 접근해야함
 */

@SuppressWarnings("unchecked")
@Slf4j
@RequestMapping(value = "/prod/async/*")
@RestController // <- Json형태로 반환     // Controller <- html을 반환
public class ProductAjaxController {

	@Autowired
	ProductServiceImpl prodService;

	//	json -> @RequestBody 로 받아야함
	//	x-www-urlencoded -> @RequestParam 로 받아야함
	//	RestController
	@PutMapping("/upStatus")
	public boolean upStatus(@RequestBody ProductInformation productInformation) {

		ProductTitle productTitle = new ProductTitle(productInformation.getProduct_code(),
				productInformation.getProduct_status());

		prodService.upStatus(productTitle);

		return true;
	}

	// 상품코드 중복검사
	@GetMapping("/overlapCheck")
	public JSONObject overlapCheck(@RequestParam Map<String, Object> map) {
		log.debug("Request Param : " + map);
		JSONObject jobj = new JSONObject();
		jobj.put("code", 400);
		int rs = prodService.overlapCheck(map);
		if(rs == 0) jobj.put("code", 200);
		return jobj;
	}

	@Transactional()
	@PostMapping(value = "/prodAdd")
	public JSONObject prodAdd(@RequestParam Map<String, Object> map) {

		log.info("Request Parameter : " + map);
		JSONObject jobj = new JSONObject();
		jobj.put("code", 999);
		if(map == null) return jobj;

		int addResult = prodService.addProductData(map);

		if(addResult > 0)
			jobj.put("code", 200);
		else if(addResult == 0)
			jobj.put("code", 300);
		else if(addResult == -1)
			jobj.put("code", 400);

		return jobj;
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
