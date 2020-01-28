package com.wooriat.admin.service;

import com.wooriat.admin.dto.SessionVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public interface LoginService {
    public void adminInfoSetSessionAttr(SessionVo sessionVo, HttpServletRequest httpSvltReq) throws Exception;
    public Map<String, Object> loginUser(Map<String, String> paramso, HttpServletRequest httpSvltReq, HttpServletResponse httpSvltRes) throws Exception;
    public void logout(Map<String, Object> params);
}
