package com.wooriat.admin.constant;


/** ========================================================================================
 * @Package Name   : com.wooriat.admin.constant
 * @FileName  : AdminConst.java
 * @Date      :
 * @Author    : ose
 * @Desc      : Admin Web Page Const Definition
 * ========================================================================================
 * 수정일         				작성자            					 내용     
 * ----------------------------------------------------------------------------------------
 * 
 * ========================================================================================
 */
public class AdminConst {
	
	 /** 로그인 URL	 */
	public static final String LOGIN_URL = "/admin/loginView";
	public static final String MAIN_URL = "/";
	 /** 세션 KEY	 */
	public static final String SESSION_NAME = "SESSION_USERINFO";

	/** 정렬 KEY 값 */
	public static final int    SORT_DEFAULT_SIZE_10 = 10;
	public static final String SERVICE_SORT_KEY = "seqId";
	public static final String PR_SORT_KEY = "prSeqId";
	
	/** AdminUser 정렬 KEY 값 */
	public static final String ADMIN_USER_SORT_KEY = "cretDtm";

}

