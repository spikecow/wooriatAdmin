package com.wooriat.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.wooriat.admin.domain.adminuser.TbAdmUserMgt;
import com.wooriat.admin.repository.AdmUserMgtRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wooriat.admin.common.exception.NotExistDataException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {
	
	private final AdmUserMgtRepository admUserMgtRepository;

	@Override
	@Transactional
	public boolean insertAdminUser(TbAdmUserMgt tbAdmUserMgt) {
		// TODO Auto-generated method stub
		log.info("insertAdminUser start !");
		
		Optional<TbAdmUserMgt> byId = admUserMgtRepository.findById(tbAdmUserMgt.getUserId());
		
		if(byId.isPresent()) {
			return false;
		}
		
//		TbAdmUserMgt originTbAdmUserMgt = (TbAdmUserMgt)byId.get();
		
//		tbAdmUserMgt.setCretUserId(originTbAdmUserMgt.getCretUserId());
		
		TbAdmUserMgt savedAdmUserMgt = admUserMgtRepository.save(tbAdmUserMgt);
		
		log.info("SavedAdmUserMgt : {} ", savedAdmUserMgt);
		
		
		return true;
	}
	
	@Override
	@Transactional
	public boolean updateAdminUser(TbAdmUserMgt tbAdmUserMgt) {
		// TODO Auto-generated method stub
		log.info("updateAdminUser start !");
		
		Optional<TbAdmUserMgt> byId = admUserMgtRepository.findById(tbAdmUserMgt.getUserId());
		
		if(!byId.isPresent()) {
			return false;
		}
		
		TbAdmUserMgt originTbAdmUserMgt = (TbAdmUserMgt)byId.get();
		
		tbAdmUserMgt.setCretUserId(originTbAdmUserMgt.getCretUserId());
			
		TbAdmUserMgt savedAdmUserMgt = admUserMgtRepository.save(tbAdmUserMgt);
		
		log.info("SavedAdmUserMgt : {} ", savedAdmUserMgt);
		
		return true;
	}

	@Override
	public TbAdmUserMgt getDetailAdminUser(String id) {
		// TODO Auto-generated method stub
		Optional<TbAdmUserMgt> byId = admUserMgtRepository.findById(id);
		log.info("getDetailAdminUser - 1");
		if(!byId.isPresent()) {
			throw new NotExistDataException("존재하지 않는 사용자 입니다.");
		}
		log.info("getDetailAdminUser - 2");
		
		TbAdmUserMgt result = byId.get();
		
		log.info("getDetailAdminUser - 3");
		
		return result;
	}

	@Override
	@Transactional
	public void deleteAdminUser(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		log.info("deleteAdminUser delList : {}", params.get("delList"));
		
		try {
			String[] targets = params.get("delList").toString().split(",");
			List<String> deleteList = new ArrayList<String>();
			for(String targetStr : targets) {
				deleteList.add(targetStr);
			}
			
			admUserMgtRepository.deleteByUserIdIn(deleteList);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new Exception();
		}
		
		log.info("delete Complete!");
		
	}

	@Override
	public Page<TbAdmUserMgt> getAdminUserList(String searchWord, Pageable pageable) {
		// TODO Auto-generated method stub
		
		Page<TbAdmUserMgt> tbAdmUserMgtPage = null;
		if(searchWord != null && !searchWord.equals("")) {
			String sw = "%" + searchWord + "%";
			//tbAdmUserMgtPage = admUserMgtRepository.findByUserId(sw, pageable);
			tbAdmUserMgtPage = admUserMgtRepository.findByUserIdLikeOrUserNmLikeOrUserDeptNmLikeOrPhoneLike(sw, sw, sw, sw, pageable);
		}
		else {
			tbAdmUserMgtPage = admUserMgtRepository.findAll(pageable);
		}
		
		log.info("getAdminUserList Page : {}", pageable);
		
		return tbAdmUserMgtPage;
	}


	@Override
	public List<TbAdmUserMgt> getTbAdmUserMgtList() {
		return admUserMgtRepository.findAll();
	}
}
