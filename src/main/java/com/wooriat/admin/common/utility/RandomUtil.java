package com.wooriat.admin.common.utility;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

/**
 * ========================================================================================
 * 
 * @Package Name : com.wooriat.admin.common.utility
 * @FileName : RandomUtil.java
 * @Date :
 * @Author :
 * @Desc :
 *       ========================================================================================
 *       수정일 작성자 내용
 *       ----------------------------------------------------------------------------------------
 * 
 *       ========================================================================================
 */
@Component
public class RandomUtil {

	/**
	 * 문자열 A에서 Z사이의 랜덤 문자열을 구하는 기능을 제공 시작문자열과 종료문자열 사이의 랜덤 문자열을 구하는 기능
	 *
	 * @param startChr - 첫 문자
	 * @param endChr   - 마지막문자
	 * @return 랜덤문자
	 * @exception Exception
	 */
	public static String getRandomStr(char startChr, char endChr) {

		int randomInt;
		String randomStr = null;

		// 시작문자 및 종료문자를 아스키숫자로 변환한다.
		int startInt = Integer.valueOf(startChr);
		int endInt = Integer.valueOf(endChr);

		// 시작문자열이 종료문자열보가 클경우
		if (startInt > endInt) {
			throw new IllegalArgumentException("Start String: " + startChr + " End String: " + endChr);
		}

		try {
			// 랜덤 객체 생성
			SecureRandom rnd = new SecureRandom();

			do {
				// 시작문자 및 종료문자 중에서 랜덤 숫자를 발생시킨다.
				randomInt = rnd.nextInt(endInt + 1);
			} while (randomInt < startInt); // 입력받은 문자 'A'(65)보다 작으면 다시 랜덤 숫자
											// 발생.

			// 랜덤 숫자를 문자로 변환 후 스트링으로 다시 변환
			randomStr = (char) randomInt + "";
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e); // 2011.10.10 보안점검 후속조치
		}

		// 랜덤문자열를 리턴
		return randomStr;
	}

	/**
	 * 특정숫자 집합에서 랜덤 숫자를 구하는 기능 시작숫자와 종료숫자 사이에서 구한 랜덤 숫자를 반환한다
	 * 
	 * @param startNum - 시작숫자
	 * @param endNum   - 종료숫자
	 * @return 랜덤숫자
	 * @exception Exception
	 */
	public static int getRandomNum(int startNum, int endNum) {
		int randomNum = 0;

		try {
			// 랜덤 객체 생성
			SecureRandom rnd = new SecureRandom();

			do {
				// 종료숫자내에서 랜덤 숫자를 발생시킨다.
				randomNum = rnd.nextInt(endNum + 1);
			} while (randomNum < startNum); // 랜덤 숫자가 시작숫자보다 작을경우 다시 랜덤숫자를
											// 발생시킨다.
		} catch (Exception e) {
			// e.printStackTrace();
			throw new RuntimeException(e);
		}

		return randomNum;
	}

}
