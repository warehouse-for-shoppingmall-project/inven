package com.inven.controller;

import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inven.service.ProductServiceImpl;


/*
 * Class 에도 부모 annotation 설정 가능
 * ex) 여기에 RequestMapping(value="common")
 * 해두면 이 밑에 맵핑들은 다 common/___ 으로 접근해야함
 */
@Slf4j
@RestController
@RequestMapping(value = "/rest")
public class RestAPIController {

	@Resource(name="productService")
	ProductServiceImpl prodService = new ProductServiceImpl();

	/* JSONObject Key => code
	 * value 의미
	 * 200 : Success
	 * 400 : Error */

	@GetMapping
	public JSONObject get(@RequestParam Map<String, Object> map) {
		log.info("Request Parameter : " + map);

		JSONObject jobj = new JSONObject();
		jobj.put("code", 200);

		return jobj;
	}

	@PostMapping
	public JSONObject post(@RequestParam Map<String, Object> map) {
		log.info("Request Parameter : " + map);

		JSONObject jobj = new JSONObject();
		jobj.put("code", 200);

		return jobj;
	}

	@PutMapping
	public JSONObject put(@RequestParam Map<String, Object> map) {
		log.info("Request Parameter : " + map);

		JSONObject jobj = new JSONObject();
		jobj.put("code", 200);

		return jobj;
	}

	@DeleteMapping
	public JSONObject delete(@RequestParam Map<String, Object> map) {
		log.info("Request Parameter : " + map);

		JSONObject jobj = new JSONObject();
		jobj.put("code", 200);

		return jobj;
	}

	@PatchMapping
	public JSONObject patch(@RequestParam Map<String, Object> map) {
		log.info("Request Parameter : " + map);

		JSONObject jobj = new JSONObject();
		jobj.put("code", 200);

		return jobj;
	}
}
