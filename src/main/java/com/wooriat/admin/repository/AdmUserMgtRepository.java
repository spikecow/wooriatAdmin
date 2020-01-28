package com.wooriat.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wooriat.admin.domain.adminuser.TbAdmUserMgt;

public interface AdmUserMgtRepository extends JpaRepository<TbAdmUserMgt,String> {
	public Page<TbAdmUserMgt> findByUserId(String userSeqId, Pageable page);
	public TbAdmUserMgt findByUserId(String userSeqId);
	public void deleteByUserIdIn(List<String> userId);
	public Page<TbAdmUserMgt> findByUserIdLikeOrUserNmLikeOrUserDeptNmLikeOrPhoneLike(String sw1, String sw2, String sw3, String sw4, Pageable page);
	//public TbAdmUserMgt findByTbAdmUserKeyUserId(TbAdmUserKey tbAdmUserKey);
	//public TbAdmUserMgt findByTbAdmUserKeyUserId(String userId);


	public TbAdmUserMgt findByUserIdAndUserPwd(String userId, String userPw);

}
