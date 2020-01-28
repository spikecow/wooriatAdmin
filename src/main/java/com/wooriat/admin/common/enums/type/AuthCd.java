package com.wooriat.admin.common.enums.type;

import com.wooriat.admin.common.enums.AdmEnum;

/** ========================================================================================
 * @Package Name   : com.wooriat.admin.common.enums.type
 * @FileName  : LangCd.java
 * @Date      :
 * @Author    : ose 
 * @Desc      : Lang Code Definition
 * ========================================================================================
 * 수정일         				작성자            					 내용     
 * ----------------------------------------------------------------------------------------
 * 
 * ========================================================================================
 */
public enum AuthCd implements AdmEnum {
	
	//언어코드
	S("S","슈퍼 관리자"),
	A("A","일반 관리자");
	
	private String value;
	
	private String description;
	
	private AuthCd(String value, String description) {
		this.value = value;
		this.description = description;
	}
	
	@Override
	public String getValue() {
		return value;
	}
	
	@Override
	public String getDescription() {
		return description;
	}
}
