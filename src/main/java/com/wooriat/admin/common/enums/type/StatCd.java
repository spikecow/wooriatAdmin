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
public enum StatCd implements AdmEnum {

	START("START","진행"),
	WAIT("WAIT","대기"),
	END("END","종료");

	private String value;

	private String description;

	private StatCd(String value, String description) {
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
