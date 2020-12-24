package com.inven.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("sessionCheck : " + request.getSession().getAttribute("connect"));
        try {
            //userData 세션key를 가진 정보가 널일경우 로그인페이지로 이동
            if(request.getSession().getAttribute("connect") == null){
                log.error("can not search session ... ");
                response.sendRedirect("/");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}