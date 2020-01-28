package com.wooriat.admin.domain;

import com.wooriat.admin.common.enums.type.AuthCd;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_user")
public class TbUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "user_id", length = 100, unique=true, nullable = false)
	private String userId;
	
	@Column(name = "user_pwd", length = 256, nullable = false)
	private String userPwd;
	
	@Column(name = "user_nm", length = 50)
	private String userNm;
	
	@Column(name = "dept_nm", length = 100)
	private String deptNm;
	
	@Column(name = "email",unique=true, length = 100)
	private String email;

	@Column(name = "auth_cd", length = 1)
	@Enumerated(EnumType.STRING)
	private AuthCd authCd;

	@CreationTimestamp
    @Column(name = "cret_dtm", updatable = false)
    private LocalDateTime cretDtm;

    @UpdateTimestamp
    @Column(name = "mdfy_dtm")
    private LocalDateTime mdfyDtm;

    @Column(name = "mdfy_user_id", length = 20)
    private String mdfyUserId;

	@Column(name = "last_login_dtm")
	private LocalDateTime lastLoginDtm;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_user_menu",
			joinColumns = @JoinColumn(name = "id"),
			inverseJoinColumns = @JoinColumn(name = "menu_id")
	)
	private List<TbMenu> userMenus = new ArrayList<>();

	@Builder
	public TbUser(Long id, String userId, String userPwd, String userNm, String deptNm, String email, AuthCd authCd, List<TbMenu> userMenus) {

		this.id = id;
		this.userId = userId;
		this.userNm = userNm;
		this.deptNm = deptNm;
		this.email = email;
		this.authCd = authCd;
		this.userPwd = userPwd;
		this.userMenus = userMenus;
	}

	public void setLastLogin(){
		this.lastLoginDtm = LocalDateTime.now();
	}

}
