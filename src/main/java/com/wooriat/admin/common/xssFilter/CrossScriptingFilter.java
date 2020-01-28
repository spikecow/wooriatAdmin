package com.wooriat.admin.common.xssFilter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.wooriat.admin.constant.AdminConst;
import org.springframework.stereotype.Component;


@Component
public class CrossScriptingFilter implements Filter {

	public FilterConfig filterConfig;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		String path = ((HttpServletRequest) request).getServletPath();
		if (path.equals(AdminConst.LOGIN_URL)){
			// 필터기능 제외
			 chain.doFilter(request, response);
			 return;
		}else{
			// 필터
			chain.doFilter(new RequestWrapper((HttpServletRequest) request),response);
		}
		
	}

}