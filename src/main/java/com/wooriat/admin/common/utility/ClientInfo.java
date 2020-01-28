package com.wooriat.admin.common.utility;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/**
 * ========================================================================================
 * 
 * @Package Name : com.wooriat.admin.common.utility
 * @FileName : ClientInfo.java
 * @Date :
 * @Author :
 * @Desc : 클라이언트(Client)의 IP주소, 웹브라우저정보를 조회
 *       ========================================================================================
 *       수정일 작성자 내용
 *       ----------------------------------------------------------------------------------------
 * 
 *       ========================================================================================
 */
@Component
public class ClientInfo {

	/**
	 * 클라이언트(Client)의 IP주소를 조회하는 기능
	 * 
	 * @param HttpServletRequest request Request객체
	 * @return String ipAddr IP주소
	 * @exception Exception
	 */
	public static String getClntIP(HttpServletRequest request) throws Exception {
		String ipAddr = request.getRemoteAddr();
		return ipAddr;
	}

	/**
	 * 클라이언트(Client)의 웹브라우저 종류를 조회하는 기능
	 * 
	 * @param HttpServletRequest request Request객체
	 * @return String webKind 웹브라우저 종류
	 * @exception Exception
	 */
	public static String getClntWebKind(HttpServletRequest request) throws Exception {

		String user_agent = request.getHeader("user-agent");

		// 웹브라우저 종류 조회
		String webKind = "";
		if (user_agent.toUpperCase().indexOf("GECKO") != -1) {
			if (user_agent.toUpperCase().indexOf("NESCAPE") != -1) {
				webKind = "Netscape (Gecko/Netscape)";
			} else if (user_agent.toUpperCase().indexOf("FIREFOX") != -1) {
				webKind = "Mozilla Firefox (Gecko/Firefox)";
			} else {
				webKind = "Mozilla (Gecko/Mozilla)";
			}
		} else if (user_agent.toUpperCase().indexOf("MSIE") != -1) {
			if (user_agent.toUpperCase().indexOf("OPERA") != -1) {
				webKind = "Opera (MSIE/Opera/Compatible)";
			} else {
				webKind = "Internet Explorer (MSIE/Compatible)";
			}
		} else if (user_agent.toUpperCase().indexOf("SAFARI") != -1) {
			if (user_agent.toUpperCase().indexOf("CHROME") != -1) {
				webKind = "Google Chrome";
			} else {
				webKind = "Safari";
			}
		} else if (user_agent.toUpperCase().indexOf("THUNDERBIRD") != -1) {
			webKind = "Thunderbird";
		} else {
			webKind = "Other Web Browsers";
		}
		return webKind;
	}

	/**
	 * 클라이언트(Client)의 웹브라우저 버전을 조회하는 기능
	 * 
	 * @param HttpServletRequest request Request객체
	 * @return String webVer 웹브라우저 버전
	 * @exception Exception
	 */
	public static String getClntWebVer(HttpServletRequest request) throws Exception {

		String user_agent = request.getHeader("user-agent");

		String webVer = "";
		String[] arr = { "MSIE", "OPERA", "NETSCAPE", "FIREFOX", "SAFARI" };
		for (int i = 0; i < arr.length; i++) {
			int s_loc = user_agent.toUpperCase().indexOf(arr[i]);
			if (s_loc != -1) {
				int f_loc = s_loc + arr[i].length();
				webVer = user_agent.toUpperCase().substring(f_loc, f_loc + 5);
				webVer = webVer.replaceAll("/", "").replaceAll(";", "").replaceAll("^", "").replaceAll(",", "")
						.replaceAll("//.", "");
			}
		}
		return webVer;
	}
}
