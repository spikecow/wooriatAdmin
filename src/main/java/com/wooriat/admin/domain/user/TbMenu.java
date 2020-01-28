package com.wooriat.admin.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_menu")
public class TbMenu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menu_id")
	private Long menuId;

	@Column(name = "menu_nm", length = 256)
	private String menuNm;

	@Column(name = "upper_menu_id")
	private Long upperMenuId;

	@Column(name = "menu_order")
	private Long menuOrder;

	@Column(name = "menu_path", length = 256)
	private String menuPath;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "userMenus")
	private List<TbUser> users = new ArrayList<>();

	@Builder
	public TbMenu (Long menuId){
		this.menuId = menuId;
	}
}
