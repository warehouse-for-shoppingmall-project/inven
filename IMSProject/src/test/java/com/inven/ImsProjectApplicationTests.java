package com.inven;

import java.util.List;
import java.util.Map;

import com.inven.common.model.ProductDetail;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestParam;

import com.inven.service.ProductServiceImpl;
import org.springframework.web.servlet.ModelAndView;

@SpringBootTest
class ImsProjectApplicationTests {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductServiceImpl prodService;

//	@Test
	void contextLoads() {
	}

	@Test
	public void test() {
		List<ProductDetail> details = prodService.selectDetail("SHY001");

		log.info("상품수정 detail 조회결과 : " + details);
	}


}
