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
public enum QuestionType implements AdmEnum {

	A("A","신규사업"),
	B("B","진행사업"),
	C("C","일반");

	private String value;

	private String description;

	private QuestionType(String value, String description) {
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
