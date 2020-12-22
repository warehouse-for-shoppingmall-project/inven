package com.inven.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inven.service.RequestServiceImpl;


/*
 * Class 에도 부모 annotation 설정 가능
 * ex) 여기에 RequestMapping(value="common")
 * 해두면 이 밑에 맵핑들은 다 common/___ 으로 접근해야함
 */

@SuppressWarnings("unchecked")
@RequestMapping(value = "/req/async/*")
@RestController
public class RequestAjaxController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name="requestService")
	RequestServiceImpl reqService = new RequestServiceImpl();

	/* JSONObject Key => code
	 * value 의미
	 * 200 : Success
	 * 400 : Error */


	@PostMapping("/upStatus")
	public JSONObject upStatus(@RequestParam Map<String, Object> map) {
		log.info("Request Parameter : " + map);
		JSONObject jobj = new JSONObject();
		jobj.put("code", 400);

		int rs = reqService.upStatus(map);
		if(rs > 0)
			jobj.put("code", 200);

		return jobj;
	}

	@GetMapping(value = "/makeReqCode")
	public JSONObject makeReqCode() {
		log.info("Request Parameter : 발주코드 생성 버튼 클릭");

		JSONObject jobj = new JSONObject();
		jobj.put("code", 400);

		String request_code = reqService.makeReqCode();
		if(request_code != null) {
			jobj.put("code", 200);
			jobj.put("request_code", request_code);
		}

		return jobj;
	}

	@GetMapping(value = "/add")
	public JSONObject add(@RequestParam Map<String, Object> map) {
		log.info("Request Parameter : " + map);
		JSONObject jobj = new JSONObject();

		jobj.put("code", 400);

		List<Map<String, Object>> addTitle = reqService.addTitle(map);
		List<Map<String, Object>> addDetail = reqService.addDetail(map);

		if(addTitle != null && addDetail != null) {
			jobj.put("code", 200);
		}

		return jobj;
	}
}
