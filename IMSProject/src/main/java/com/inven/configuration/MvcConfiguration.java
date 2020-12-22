package com.inven.configuration;

import com.inven.common.logger.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.inven.common.logger.LoggerInterceptor;

import java.util.List;

@Slf4j
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

	@Value("${interceptor.exclude}") // application.properties에 설정된 값을 가지고 오기
	private List<String> exclude;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**").excludePathPatterns(exclude);
		exclude.add("/");
		exclude.add("/error");
		exclude.add("/loginCheck");
		exclude.add("/logout");
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(exclude);
	}

}