package com.inven.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.inven.service.inter.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inven.common.CommandMap;


/*
 * Class 에도 부모 annotation 설정 가능
 * ex) 여기에 RequestMapping(value="common")
 * 해두면 이 밑에 맵핑들은 다 common/___ 으로 접근해야함
 */

@SuppressWarnings("unchecked")
@Slf4j
@Controller
public class HomeController {

    @Autowired
    CommonService commonService;

    /*
     * url annotation
     * 복수 지정방식 : value = { "url", "url", "url"}
     * 단일 지정방식 : value = "url"
     * */

    @GetMapping(value = {"/", "login"})
    public ModelAndView home(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        if(req.getSession().getAttribute("connect") != null)
            mv.setViewName("redirect:/req/list");

        return mv;
    }

    @GetMapping(value = "logout")
    public ModelAndView logout(HttpServletRequest req) {
        HttpSession s = req.getSession();
        if(s.getAttribute("connect") != null)
            s.invalidate();
        return new ModelAndView("redirect:/");
    }

    @GetMapping(value = "main")
    public ModelAndView movePageMain(@RequestParam Map<String, Object> map) {
        return new ModelAndView("pageMain");
    }

	@ResponseBody
	@GetMapping(value="readFile")
	public String readFileTest() throws IOException {
		DefaultResourceLoader drl = new DefaultResourceLoader();
		Resource resource = drl.getResource("classpath:static/pass/pwd.txt");

		return Files.readString(Path.of(resource.getURI()));
	}

    /* 비동기 서버통신(Ajax) 접근할 때 @ResponseBody */
    @ResponseBody
    @RequestMapping(value = "loginCheck", method = RequestMethod.GET)
    public JSONObject loginCheck(@RequestParam Map<String, Object> map, HttpServletRequest req) throws IOException {
        log.debug("Request Parameter : " + map);
        JSONObject jobj = new JSONObject();
        jobj.put("code", 400);
        if (map.containsKey("pwd")) {
            boolean rs = commonService.loginCheck(map);
            if (rs) {
                jobj.put("code", 200);
                HttpSession s = req.getSession();
                s.setAttribute("connect", true);
                s.setMaxInactiveInterval(60 * 60);
            }
        }
        return jobj;
    }

    @RequestMapping(value = "/sample/testMapArgumentResolver")
    public ModelAndView testMapArgumentResolver(CommandMap commandMap) throws Exception {
        ModelAndView mv = new ModelAndView();
        if (!commandMap.isEmpty()) {
            Iterator<Entry<String, Object>> iterator = commandMap.getMap().entrySet().iterator();
            Entry<String, Object> entry;
            while (iterator.hasNext()) {
                entry = iterator.next();
                log.debug("key : " + entry.getKey() + ", value : " + entry.getValue());
            }
        }
        return mv;
    }

}
