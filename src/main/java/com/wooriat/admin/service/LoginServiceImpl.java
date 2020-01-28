package com.wooriat.admin.service;

import com.wooriat.admin.common.CookieManager;
import com.wooriat.admin.common.utility.AES128Cipher;
import com.wooriat.admin.common.utility.SessionUtil;
import com.wooriat.admin.domain.adminuser.TbAdmUserMgt;
import com.wooriat.admin.domain.login.LoginVo;
import com.wooriat.admin.repository.AdmUserMgtRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
	
	private final AdmUserMgtRepository admUserMgtRepository;

	@Override
	public void adminInfoSetSessionAttr(LoginVo loginVo, HttpServletRequest httpSvltReq) throws Exception {
		httpSvltReq.getSession().setAttribute("SESSION_USERINFO", loginVo);
		httpSvltReq.getSession().setAttribute("userId", loginVo.getUserId());
		httpSvltReq.getSession().setMaxInactiveInterval(60 * 60);
	}

	@Override
	public Map<String, Object> loginUser(Map<String, String> params, HttpServletRequest httpSvltReq, HttpServletResponse httpSvltRes) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		String jSession = CookieManager.getCookieValue(httpSvltReq,"JSESSIONID");
		String userId = params.get("userId");
		Optional<TbAdmUserMgt> byId = admUserMgtRepository.findById(userId);
		if(!byId.isPresent()){
			returnMap.put("status", "fail");
			// 존재하지않는 아이디
			returnMap.put("msg", "존재하지 않는 사용자 입니다.");
			return  returnMap;
		}

		String userPw = AES128Cipher.AES_Decode((String)params.get("userPw"),"abcdefghijklmnop");
		TbAdmUserMgt byUserIdAndUserPwd = admUserMgtRepository.findByUserIdAndUserPwd(userId, userPw);

		if(byUserIdAndUserPwd == null){
			returnMap.put("status", "fail");
			// 잘못된 패스워드
			returnMap.put("msg", "비밀번호를 잘못 입력하셨습니다.");
			return  returnMap;
		}


		LoginVo loginVo = SessionUtil.getUserInfo(httpSvltReq);
		loginVo.setUserId(userId);
		loginVo.setUserPw((String)params.get("userPw"));
		loginVo.setJSessionId(jSession);
		loginVo.setAuthDivCd("S");

		returnMap.put("status", "success");
		returnMap.put("msg", "로그인 성공!");

		adminInfoSetSessionAttr(loginVo, httpSvltReq);
		return  returnMap;

	}

	@Override
	public void logout(Map<String, Object> params) {

	}
}
