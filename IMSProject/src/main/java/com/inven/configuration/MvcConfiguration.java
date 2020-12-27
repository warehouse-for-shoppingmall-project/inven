package com.inven.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.inven.common.interceptor.LoginInterceptor;
import com.inven.common.interceptor.LoggerInterceptor;

import java.util.List;

@Slf4j
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

	@Value("${interceptor.exclude}") // application.properties에 설정된 값을 가지고 오기
	private List<String> exclude;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		exclude.add("/favicon.ico");
		exclude.add("/error");
		registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**").excludePathPatterns(exclude);
		exclude.add("/");
		exclude.add("/loginCheck");
		exclude.add("/logout");
//		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(exclude);
	}
}