package com.wooriat.admin.service;

import com.wooriat.admin.common.CookieManager;
import com.wooriat.admin.common.utility.AES128Cipher;
import com.wooriat.admin.common.utility.SessionUtil;
import com.wooriat.admin.domain.TbUser;
import com.wooriat.admin.dto.SessionVo;
import com.wooriat.admin.dto.UserDto;
import com.wooriat.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
	
	private final UserRepository userRepository;

	@Override
	public void adminInfoSetSessionAttr(SessionVo sessionVo, HttpServletRequest httpSvltReq) throws Exception {
		httpSvltReq.getSession().setAttribute("SESSION_USERINFO", sessionVo);
		httpSvltReq.getSession().setAttribute("userId", sessionVo.getUserId());
		httpSvltReq.getSession().setMaxInactiveInterval(60 * 60);
	}

	@Override
	public Map<String, Object> loginUser(Map<String, String> params, HttpServletRequest httpSvltReq, HttpServletResponse httpSvltRes) throws Exception {
		Map<String, Object> returnMap = new HashMap<>();
		String jSession = CookieManager.getCookieValue(httpSvltReq,"JSESSIONID");
		String userId = params.get("userId");

		String userPw = AES128Cipher.AES_Decode((String)params.get("userPw"),"abcdefghijklmnop");
		TbUser byUserIdAndUserPwd = userRepository.findByUserIdAndUserPwd(userId, userPw);

		if(byUserIdAndUserPwd == null){
			returnMap.put("status", "fail");
			returnMap.put("msg", "입력하신 아이디 또는 비밀번호가 맞지 않습니다. 확인 후 다시 입력해 주세요.");
			return  returnMap;
		}

		byUserIdAndUserPwd.setLastLogin();
		TbUser user = userRepository.save(byUserIdAndUserPwd);

		SessionVo sessionVo = SessionUtil.getUserInfo(httpSvltReq);
		sessionVo.setUserId(userId);
		sessionVo.setJSessionId(jSession);
		sessionVo.setEmail(user.getEmail());
		sessionVo.setDeptNm(user.getDeptNm());
		sessionVo.setAuthCd(user.getAuthCd().getValue());
		sessionVo.setLastLoginDtm(user.getLastLoginDtm());

		returnMap.put("status", "success");
		returnMap.put("msg", "로그인 성공!");

		adminInfoSetSessionAttr(sessionVo, httpSvltReq);
		return  returnMap;

	}

	@Override
	public void logout(Map<String, Object> params) {

	}
}
