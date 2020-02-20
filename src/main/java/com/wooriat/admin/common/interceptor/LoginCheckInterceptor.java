package com.wooriat.admin.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wooriat.admin.common.utility.SessionUtil;
import com.wooriat.admin.constant.AdminConst;
import com.wooriat.admin.dto.SessionVo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		SessionVo sessionVo = (SessionVo) SessionUtil.get(request, AdminConst.SESSION_NAME);

		if (sessionVo != null) {
			request.setAttribute("userLvl", sessionVo.getAuthCd());
			request.setAttribute("sessionVo", sessionVo);
			if(!(request.getRequestURI().indexOf("/admin/loginView") < 0)){
				log.info("Already login! Redirect MainView");

				String url = "/myinfo/check";
				for(Long menuId:sessionVo.getMenuId()){
					if(menuId == 5){ // 메인 팝업 관리
						url = "/popup/list";
						break;
					}else if(menuId == 6){ // 경영현황
						url = "/notice/list?menuCd=M";
						break;
					}else if(menuId == 7){ // 회사소식
						url = "/notice/list?menuCd=C";
						break;
					}else if(menuId == 8){ // Photo소식
						url = "/notice/list?menuCd=P";
						break;
					}else if(menuId == 9){ // 사회공헌
						url = "/notice/list?menuCd=S";
						break;
					}else if(menuId == 10){ // 분양물건정보
						url = "/SaleItem/list";
						break;
					}else if(menuId == 11){ // 공매물건정보
						url = "/ShortSell/list";
						break;
					}else if(menuId == 12){ // 고객문의
						url = "/qa/list";
						break;
					}

				}
				response.sendRedirect(url);
				return false;
			}
		} else {
			if(request.getRequestURI().indexOf("/admin") < 0){
				log.info("Not login! Redirect LogInView");
				response.sendRedirect(AdminConst.LOGIN_URL);
				return false;
			}
		}

		return true;
	}
}

