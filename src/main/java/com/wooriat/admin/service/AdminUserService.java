package com.wooriat.admin.service;

import java.util.List;
import java.util.Map;

import com.wooriat.admin.domain.adminuser.TbAdmUserMgt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AdminUserService {
	
	public boolean updateAdminUser(TbAdmUserMgt tbAdmUserMgt);
	public boolean insertAdminUser(TbAdmUserMgt tbAdmUserMgt);
    public TbAdmUserMgt getDetailAdminUser(String id);
    public void deleteAdminUser(Map<String, Object> params) throws Exception;
    public Page<TbAdmUserMgt> getAdminUserList(String searchWord,Pageable pageable);
    public List<TbAdmUserMgt> getTbAdmUserMgtList();
}
