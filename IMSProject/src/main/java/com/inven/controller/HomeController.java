package com.inven.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.inven.mapper.CommonMapper;
import com.inven.service.CommonServiceImpl;
import com.inven.service.inter.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    // 파일 변경 방식 사용 시 commonService로 함수 변경 하면 됨.
    @Autowired
    CommonServiceImpl commonService;

    // Impl 은 놔두고 여기다가 만들면 됨
    @Autowired
    CommonService service = new CommonService() {

        @Autowired
        CommonMapper mapper;

        @Override
        public boolean loginCheck(Map<String, Object> map) {
            return mapper.loginCheck(map.get("pwd").toString()) == 1;
        }

        @Override
        public boolean loginChange(Map<String, Object> map) {
            return mapper.loginChange(map) == 1;
        }
    };

    @GetMapping(value = {"/", "login"})
    public ModelAndView home(HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("login");

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

    @GetMapping(value="changePass")
    public String changePass(){
        return "change_pass";
    }

	@ResponseBody
	@GetMapping(value="readFile")
	public String readFileTest() throws IOException {
		DefaultResourceLoader drl = new DefaultResourceLoader();
		Resource resource = drl.getResource("classpath:static/resources/pass/pwd.txt");

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
            boolean rs = service.loginCheck(map);
            if (rs) {
                jobj.put("code", 200);
                HttpSession s = req.getSession();
                s.setAttribute("connect", true);
                s.setMaxInactiveInterval(60*60*12);
            }
        }
        return jobj;
    }

    @ResponseBody
    @PutMapping(value="changePass")
    public JSONObject changePass(@RequestParam Map<String, Object> map) throws IOException {
        log.debug("Request Parameter : " + map);
        JSONObject jobj = new JSONObject();

        jobj.put("code", 400);
        if (map.containsKey("pwd")) {
            boolean rs = service.loginCheck(map);
            log.info("loginCheck 는? : " + rs);
            if (rs) {
                boolean lc = service.loginChange(map);
                log.info("loginChange 는? : "+ lc);
                if(lc){
                    jobj.put("code", 200);
                }
            }else{
                jobj.put("code", 300);
            }
        }
        return jobj;
    }

}
