package com.inven.controller;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/rest")
public class RestAPIController {

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
