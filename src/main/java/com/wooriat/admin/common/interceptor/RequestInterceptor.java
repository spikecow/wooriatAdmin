package com.wooriat.admin.common.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wooriat.admin.common.utility.SessionUtil;
import com.wooriat.admin.domain.login.LoginVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wooriat.admin.common.utility.StringUtil;

import lombok.extern.slf4j.Slf4j;

/** ========================================================================================
 * @Package Name   : com.wooriat.admin.common.interceptor
 * @FileName  : RequestInterceptor.java
 * @Date      :
 * @Author    : 
 * @Desc      : RequestInterceptor Interceptor
 * ========================================================================================
 * 수정일         				작성자            					 내용     
 * ----------------------------------------------------------------------------------------
 * 
 * ========================================================================================
 */
@Slf4j
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (log.isInfoEnabled()) {
			StringBuilder builder = new StringBuilder();
			builder.append("\n=============== Request URI ================\n").append(' ')
					.append(request.getRequestURI()).append('\n').append(" Client IP      : ")
					.append(request.getRemoteAddr()).append(':').append(request.getRemotePort()).append('\n')
					.append(" Method         : ").append(request.getMethod()).append('\n');
			builder.append("=========== Request Parameters =============\n");
			for (Enumeration<String> enumer = request.getParameterNames(); enumer.hasMoreElements();) {
				String name = enumer.nextElement();

				if (!StringUtil.chkNum(name)) {

					if (request.getParameterValues(name).length > 1) {
						builder.append(' ').append(StringUtils.rightPad(name + "[]", 14)).append(" : ");
						for (int i = 0; i < request.getParameterValues(name).length; i++) {
							builder.append((request.getParameterValues(name))[i]);
							if (i == request.getParameterValues(name).length - 1) {
								builder.append('\n');
							} else {
								builder.append(", ");
							}
						}
					} else {
						builder.append(' ').append(StringUtils.rightPad(name, 14)).append(" : ").append(request.getParameter(name)).append('\n');
					}
				}
			}
			builder.append("============================================");
			log.info(builder.toString());
		}

		if(!(request.getRequestURI().indexOf("/manager") < 0)) {
			LoginVo loginVo = SessionUtil.getUserInfo(request);
			if(!loginVo.getAuthDivCd().equals("S") ){
				response.setStatus(403);
				return false;
			}
		}

		request.setAttribute("transactionTime", System.currentTimeMillis());
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("###############  Interceptor > postHandle");

		super.postHandle(request,response,handler,modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception arg3) throws Exception {
		if (log.isInfoEnabled()) {
				log.info("*********turnaround time : "+ (System.currentTimeMillis() - (Long) request.getAttribute("transactionTime")));
		}
		super.afterCompletion(request,response,object,arg3);
	}
}

