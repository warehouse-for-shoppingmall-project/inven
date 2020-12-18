package com.inven.controller;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inven.common.CommandMap;
import com.inven.common.CommonUtils;
import com.inven.service.CommonServiceImpl;


/*
 * Class 에도 부모 annotation 설정 가능
 * ex) 여기에 RequestMapping(value="common")
 * 해두면 이 밑에 맵핑들은 다 common/___ 으로 접근해야함
 */

@SuppressWarnings("unchecked")
@Controller
public class HomeController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource(name="commonService")
	CommonServiceImpl commonService = new CommonServiceImpl();


	/*
	 * url annotation
	 * 복수 지정방식 : value = { "url", "url", "url"}
	 * 단일 지정방식 : value = "url"
	 * */

	@GetMapping(value = {"/", "/index", "index.do"})
	public ModelAndView home(@RequestParam Map<String, Object> map) {
		log.debug("Request Parameter : " + map);

		ModelAndView mv = new ModelAndView("index");

		return mv;
	}

	@RequestMapping(value = "main.do" , method = RequestMethod.GET)
	public ModelAndView movePageMain(@RequestParam Map<String, Object> map) {
		return new ModelAndView("pageMain");
	}

	/* 비동기 서버통신(Ajax) 접근할 때 @ResponseBody */
	@ResponseBody
	@RequestMapping(value = "loginCheck.do", method = RequestMethod.GET)
	public JSONObject loginCheck(@RequestParam Map<String, Object> map, HttpServletRequest req) {
		log.debug("Request Parameter : " + map);
		// JSONObject = {key : value, key : value.....} 형식으로 저장됨 하나의 중괄호 안에 여러가지의 key, value를 적재함
		JSONObject jobj = new JSONObject();
		jobj.put("code", 400); // key 값이 중복되면 덮어씌워짐
		if(map.containsKey("pwd")) {
			map.put("pwd", CommonUtils.getEncrypt(map.get("pwd").toString(), "cloth")); // 암호화 CommonUtils에 있음. 구글링으로 이게 뭔지만 알면 됨
			Map<String, Object> rs = commonService.loginCheck(map); // ___Service -> ___DAO -> ___SQL.xml 순으로 접근함 
			if(rs != null) {
				jobj.put("code", 200);	// 정의된 요소값을 찾았는지 확인
				HttpSession s = req.getSession();
				s.setAttribute("key", "value");
			}
		}
		return jobj;
	}

	@RequestMapping(value = "/sample/testMapArgumentResolver.do")
	public ModelAndView testMapArgumentResolver(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("");
		if (!commandMap.isEmpty()) {
			Iterator<Entry<String, Object>> iterator = commandMap.getMap().entrySet().iterator();
			Entry<String, Object> entry = null;
			while (iterator.hasNext()) {
				entry = iterator.next();
				log.debug("key : " + entry.getKey() + ", value : " + entry.getValue());
			}
		}
		return mv;
	}

}
