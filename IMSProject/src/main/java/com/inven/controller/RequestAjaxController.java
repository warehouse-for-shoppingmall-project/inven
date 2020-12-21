package com.inven.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inven.service.RequestServiceImpl;

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

}
