package com.inven;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestParam;

import com.inven.service.ProductServiceImpl;

@SpringBootTest
class ImsProjectApplicationTests {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductServiceImpl prodService;
	
//	@Test
	void contextLoads() {
	}
	
	@Test
	public JSONObject test(@RequestParam Map<String, Object> map) {
		log.info("Request Parameter : " + map);
		
		JSONObject jobj = new JSONObject();
		jobj.put("code", 400); 

//		List<Map<String, Object>> list = prodService.(map);
//		if(list != null) {
//			jobj.put("code", 200);
//			jobj.put("list", list);
//		}
		
		return jobj;
	}
}
