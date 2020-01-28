package com.wooriat.admin.domain.adminuser;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "tb_adm_user_mgt")
public class TbAdmUserMgt {
	
	@Id
	@Column(name = "user_id", length = 255)
	private String userId;
	
	@Column(name = "user_pwd", length = 256)
	private String userPwd;
	
	@Column(name = "user_nm", length = 20)
	private String userNm;
	
	@Column(name = "user_dept_nm", length = 100)
	private String userDeptNm;
	
	@Column(name = "email", length = 256)
	private String email;
	
	@Column(name = "phone", length = 256)
	private String phone;
	
	@Column(name = "auth_div_cd", length = 1)
	private String authDivCd;
	
	@CreationTimestamp
    @Column(name = "cret_dtm", updatable = false)
    private LocalDateTime cretDtm;

    @UpdateTimestamp
    @Column(name = "mdfy_dtm")
    private LocalDateTime mdfyDtm;

    @Column(name = "cret_user_id", length = 20)
    private String cretUserId;

    @Column(name = "mdfy_user_id", length = 20)
    private String mdfyUserId;

	@Override
	public String toString() {
		return "TbAdmUserMgt [userId=" + userId + ", userPwd=" + userPwd + ", userNm=" + userNm + ", userDeptNm="
				+ userDeptNm + ", email=" + email + ", phone=" + phone + ", authDivCd=" + authDivCd + ", cretDtm="
				+ cretDtm + ", mdfyDtm=" + mdfyDtm + ", cretUserId=" + cretUserId + ", mdfyUserId=" + mdfyUserId + "]";
	}

    
}
