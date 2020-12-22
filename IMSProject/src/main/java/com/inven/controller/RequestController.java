package com.inven.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.inven.service.RequestServiceImpl;


@Controller
@RequestMapping(value = "/req/*")
public class RequestController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name="requestService")
	RequestServiceImpl reqService = new RequestServiceImpl();

	@RequestMapping(value = "/test" , method = RequestMethod.GET)
	public ModelAndView test(@RequestParam Map<String, Object> map) {
		log.info("Request Parameter : " + map);
		ModelAndView mv = new ModelAndView("/layout/write");

		List<Map<String, Object>> list = reqService.searchWhere(map);

		mv.addObject("list", list);

		return mv;
	}

	/*
	 * mapping 주소가 /list 이면서 Method는 Get이고
	 * request_fd안에 request_main.html로 가는 mv를 만들고
	 * searchTitle의 값을 받아서 mv에 담아서 보내는 함수를 만들어봐
	 * */
	// mappingurl이랑method이름을통일하면method이름이겹칠일이없다

	@RequestMapping(value = "/list" , method = RequestMethod.GET)
	public ModelAndView list(@RequestParam Map<String, Object> map) {
		log.info("Request Parameter : " + map);
		ModelAndView mv = new ModelAndView("/request_fd/request_main");

		if(!map.containsKey("confirm")) map.put("confirm", "");
		if(!map.containsKey("value")) map.put("value", "");
		
		List<Map<String, Object>> list = reqService.searchWhere(map);
		
		mv.addObject("list", list);

		return mv;
	}

	@GetMapping(value = "/add")
	public ModelAndView add(@RequestParam Map<String, Object> map) {
		log.info("Request Parameter : " + map);
		ModelAndView mv = new ModelAndView("request_fd/request_add");
		List<String> list = reqService.selectProductCode();
		
		mv.addObject("list",list);
		
		return mv;
	}

	
}
