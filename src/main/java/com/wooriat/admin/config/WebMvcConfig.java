package com.wooriat.admin.config;


import com.wooriat.admin.common.interceptor.LoginCheckInterceptor;
import com.wooriat.admin.common.interceptor.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** ========================================================================================
 * @Package Name   : com.wooriat.admin.config
 * @FileName  : WebMvcConfig.java
 * @Date      :
 * @Author    : ose
 * @Desc      : Web Mvc Config Definition
 * ========================================================================================
 * 수정일         				작성자            					 내용     
 * ----------------------------------------------------------------------------------------
 * 
 * ========================================================================================
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
	
	private final RequestInterceptor requestInterceptor;

	private final LoginCheckInterceptor loginCheckInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestInterceptor).addPathPatterns("/*").addPathPatterns("/**/*")
				.excludePathPatterns("/test/**/")
				.excludePathPatterns("/admin/logout")
				.excludePathPatterns("/bootstrap/**/*")
				.excludePathPatterns("/common/**/*")
				.excludePathPatterns("/customJS/**/*")
				.excludePathPatterns("/plugins/**/*")
				.excludePathPatterns("/se2/**/*")
				.excludePathPatterns("/plugsins/**/*");
		registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/*").addPathPatterns("/**/*")
				.excludePathPatterns("/test/**/")
				.excludePathPatterns("/bootstrap/**/*")
				.excludePathPatterns("/common/**/*")
				.excludePathPatterns("/customJS/**/*")
				.excludePathPatterns("/plugins/**/*")
				.excludePathPatterns("/se2/**/*")
				.excludePathPatterns("/plugsins/**/*");

		WebMvcConfigurer.super.addInterceptors(registry);
	}

}
