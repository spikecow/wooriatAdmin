package com.wooriat.admin.config;


import com.wooriat.admin.common.xssFilter.CrossScriptingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** ========================================================================================
 * @Package Name   : com.wooriat.admin.config
 * @FileName  : WebMvcConfig.java
 * @Date      :
 * @Author    : ose
 * @Desc      : Filter Config Definition
 * ========================================================================================
 * 수정일         				작성자            					 내용     
 * ----------------------------------------------------------------------------------------
 * 
 * ========================================================================================
 */
@Configuration
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean getFilterRegistrationBean()
	{
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new CrossScriptingFilter());
		registrationBean.addUrlPatterns("/*"); // 서블릿 등록 빈 처럼 패턴을 지정해 줄 수 있다.
		return registrationBean;
	}

}
