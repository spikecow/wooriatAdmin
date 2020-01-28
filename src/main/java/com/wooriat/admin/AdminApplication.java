package com.wooriat.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/** ========================================================================================
 * @Package Name   : com.wooriat.admin
 * @FileName  : AdminApplication.java
 * @Date      :
 * @Author    : OSE
 * @Desc      : Main  Class
 * ========================================================================================
 * 수정일         				작성자            					 내용     
 * ----------------------------------------------------------------------------------------
 * 
 * ========================================================================================
 */
/**
 * @WebFilter 를 사용하기 위한 어노테이션
 */
@ServletComponentScan
@SpringBootApplication
public class AdminApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
	    SpringApplication.run(AdminApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AdminApplication.class);
//		return super.configure(builder);
	}

}
