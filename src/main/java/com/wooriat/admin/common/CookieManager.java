package com.wooriat.admin.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {
	final static String COOKIE_NAME ="NAME";//
	final static int    COOKIE_MAX_AGE=24*60*60;

	public String getCookieValue(HttpServletRequest req){
		return getCookieValue(req,COOKIE_NAME);
	}
	
	public static String getCookieValue(HttpServletRequest req, String cookieName){
		String   cookieValue = "";
		Cookie[] cookie = req.getCookies();
		for(int i=0;cookie != null && i<cookie.length;i++){
			if(cookie[i].getName().equals(cookieName)==true){
				cookieValue = cookie[i].getValue();
			}
		}
		return cookieValue;
	}
	
	public void setCookieValue(HttpServletResponse res, String value){
		setCookieValue(res,COOKIE_NAME,7,value);
	}

	public static void setCookieValue(HttpServletResponse res, String cookieName, int cookieMaxAge, String value){
		Cookie cookie = new Cookie(cookieName,value);
		cookie.setPath("/");
		cookie.setMaxAge(cookieMaxAge*COOKIE_MAX_AGE);
		res.addCookie(cookie);
	}

	public void invalidateCookie(HttpServletResponse res) {
		invalidateCookie(res,COOKIE_NAME);
	}
	
	public static void invalidateCookie(HttpServletResponse res, String cookieName) {
		//log.info("delete cookie ");
		Cookie cookie = new Cookie(cookieName,null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		res.addCookie(cookie);
	}	
}
