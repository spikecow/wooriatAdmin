package com.wooriat.admin.common.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.stereotype.Component;


/** ========================================================================================
 * @Package Name   : com.wooriat.admin.common.utility
 * @FileName  : DateUtil.java
 * @Date      :
 * @Author    : 
 * @Desc      :
 * ========================================================================================
 * 수정일         				작성자            					 내용     
 * ----------------------------------------------------------------------------------------
 * 
 * ========================================================================================
 */
@Component
public class DateUtil {
	
	public static Locale locale = java.util.Locale.KOREAN;
	/**
	 * 기본날짜 포멧
	 */
	public static String yyyyMMdd = "yyyyMMdd";
	public static String yyyyMMddHHmm = "yyyyMMddHHmm";
	public static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	
	private static Calendar getCalendar(String dt) {
		Calendar cal = Calendar.getInstance();

		int yyyy = Integer.parseInt(dt.substring(0, 4));
		int MM = Integer.parseInt(dt.substring(4, 6)) - 1;
		int dd = Integer.parseInt(dt.substring(6, 8));

		int HH = 0;
		int mm = 0;
		int ss = 0;

		if (dt.length() == 8) {
			cal.set(yyyy, MM, dd);
		} else if (dt.length() == 12) {
			HH = Integer.parseInt(dt.substring(8, 10));
			mm = Integer.parseInt(dt.substring(10, 12));
			cal.set(yyyy, MM, dd, HH, mm);
		} else if (dt.length() == 14) {
			HH = Integer.parseInt(dt.substring(8, 10));
			mm = Integer.parseInt(dt.substring(10, 12));
			ss = Integer.parseInt(dt.substring(12, 14));
			cal.set(yyyy, MM, dd, HH, mm, ss);
		}

		return cal;
	}
	
    /**
     * 하루의 시간
     */
    private static final long ONE_DATE_TIME = 24 * 60 * 60 * 1000;

    /**
     * 현재 시간을 yyyyMMddHHmmssSSS(년월일시분초밀리초) 형태로 출력 <br/>
     * 
     * <pre></pre>
     * 
     * @return
     */
    public static String getCurrentMiliTime() {
        String currentTime = null;
        try {
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            currentTime = formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentTime;
    }

    /**
     * 현재 시간을 yyyyMMddHHmmss(년월일시분초) 형태로 출력 <br/>
     * 
     * <pre></pre>
     * 
     * @return
     */
    public static String getCurrentTime() {
        String currentTime = null;
        try {
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            currentTime = formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentTime;
    }

    /**
     * 현재 일자를 yyyy-MM-dd(년월일) 형태로 출력 <br/>
     * 
     * <pre></pre>
     * 
     * @return
     */
    public static String getCurrentDate() {
        String currentDate = null;
        try {
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            currentDate = formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentDate;
    }

    /**
     * 현재일 기준으로 입력된 숫자만큼의 일자를 입력된 형식으로 반환한다. <br/>
     * 
     * <pre></pre>
     * 
     * @param format
     * @param day
     * @return
     */
    public static String getFormatDate(String format, long day) {
        String formatDate = null;
        try {
            Date date = new Date(Calendar.getInstance().getTime().getTime() + ONE_DATE_TIME * day);
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            formatDate = formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formatDate;
    }

    /**
     * 현재일 기준으로 한달전 일자를 입력된 형식으로 반환한다. <br/>
     * 
     * <pre></pre>
     * 
     * @param format
     * @return
     */
    public static String getOneMonBeforeFormatDate(String format) {
        String formatDate = null;
        try {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            formatDate = formatter.format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formatDate;
    }

    /**
     * 현재일 기준으로 입력된 숫자에 해당하는 월일자를 입력된 형식으로 반환한다. <br/>
     * 
     * <pre></pre>
     * 
     * @param format
     * @return
     */
    public static String getMonFormatDate(String format, int month) {
        String formatDate = null;
        try {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, +month);
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            formatDate = formatter.format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formatDate;
    }

    /**
	 * 오늘날짜 : yyyyMMdd
	 *
	 * @return
	 */
	public static String getDate() {
		return getDate("yyyyMMdd");
	}
	
	/**
	 * 오늘날짜
	 *
	 * @param dateFormat
	 *            포멧
	 * @return
	 */
	public static String getDate(String dateFormat) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, locale);
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
		return sdf.format(c.getTime());
	}
	/**
	 * 날짜 포멧 바꾸기
	 *
	 * <pre>
	 * String sampleDate = convertDateFormat(&quot;20081010&quot;, &quot;yyyyMMdd&quot;, &quot;yyyy-MM-dd&quot;);
	 * </pre>
	 *
	 * @param dt
	 *            날짜
	 * @param oriFormat
	 *            원본날짜포멧
	 * @param convFormat
	 *            변경날짜포멧
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	public static String convertDateFormat(String dt, String oriFormat, String convFormat) throws Exception {
		String rt = "";

		try {
			SimpleDateFormat ori = new SimpleDateFormat(oriFormat, locale);
			Date oriDt = ori.parse(dt);
			SimpleDateFormat conv = new SimpleDateFormat(convFormat, locale);

			rt = conv.format(oriDt);
		} catch (ParseException pe) {
			throw new Exception("날짜와 포멧이 맞지 않거나 지원되지 않는 포멧입니다.");
		}

		return rt;
	}
	/**
	 * 조건일 주의 시작일
	 *
	 * <pre>
	 * 예제)
	 * String sampleDate = getFirstDayOfWeek(&quot;20081010&quot;);
	 * </pre>
	 *
	 * @param dt
	 * @return
	 * @throws Exception
	 */
	public static String getFirstDayOfWeek(String dt) throws Exception {
		String dateFormat = getDateFormat(dt);
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, locale);

		Calendar c = getCalendar(dt);
		c.add(Calendar.DATE, (c.get(Calendar.DAY_OF_WEEK) - 1) * -1);

		return sdf.format(c.getTime());
	}

	/**
	 * 조건일 주의 시작일 포멧변경
	 *
	 * <pre>
	 * 예제)
	 * String sampleDate = getFirstDayOfWeek(&quot;20081010&quot;, &quot;yyyy-MM-dd&quot;);
	 * </pre>
	 *
	 * @param dt
	 *            날짜
	 * @param dateFormat
	 *            변경할 포멧
	 * @return
	 * @throws Exception
	 */
	public static String getFirstDayOfWeek(String dt, String dateFormat) throws Exception {
		return convertDateFormat(getFirstDayOfWeek(dt), getDateFormat(dt), dateFormat);
	}

	/**
	 * 조건일 주의 종료일
	 *
	 * <pre>
	 * 예제)
	 * String sampleDate = getLastDayOfWeek(&quot;20081010&quot;);
	 * </pre>
	 *
	 * @param dt
	 *            날짜
	 * @return
	 * @throws Exception
	 */
	public static String getLastDayOfWeek(String dt) throws Exception {
		String dateFormat = getDateFormat(dt);
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, locale);

		Calendar c = getCalendar(dt);
		c.add(Calendar.DATE, 7 - c.get(Calendar.DAY_OF_WEEK));

		return sdf.format(c.getTime());
	}

	/**
	 * 조건일 주의 종료일 포멧변경
	 *
	 * <pre>
	 * 예제)
	 * String sampleDate = getLastDayOfWeek(&quot;20081010&quot;, &quot;yyyy-MM-dd&quot;);
	 * </pre>
	 *
	 * @param dt
	 *            날짜
	 * @param dateFormat
	 *            변환할 포멧
	 * @return
	 * @throws Exception
	 */
	public static String getLastDayOfWeek(String dt, String dateFormat) throws Exception {
		return convertDateFormat(getLastDayOfWeek(dt), getDateFormat(dt), dateFormat);
	}
	
	/**
	 * 날짜에 해당하는 포멧을 반환 날짜문자열 길이로 세가지중 한가지 타입선택 (yyyyMMdd, yyyyMMddHHmm,
	 * yyyyMMddHHmmss)
	 *
	 * @param dt
	 * @return
	 * @throws Exception
	 */
	private static String getDateFormat(String dt) throws Exception {
		int dtlen = dt.length();
		String dateFormat = "";
		if (dtlen == 8)
			dateFormat = yyyyMMdd;
		else if (dtlen == 12)
			dateFormat = yyyyMMddHHmm;
		else if (dtlen == 14)
			dateFormat = yyyyMMddHHmmss;
		else
			throw new Exception("허용된 날짜 포멧이 아닙니다. 가능한 포멧 : yyyyMMdd, yyyyMMddHHmm, yyyyMMddHHmmss");

		return dateFormat;
	}
	/**
	 * 지정한 날짜의 해당월의 주차 구하기
	 *
	 * <pre>
	 * 예제)
	 * int jucha = getWeek(&quot;20081010&quot;);
	 * </pre>
	 *
	 * @param dt
	 * @return
	 * @throws Exception
	 */
	public static int getWeek(String dt) throws Exception {
		getDateFormat(dt);
		Calendar c = getCalendar(dt);
		return c.get(Calendar.WEEK_OF_MONTH);
	}
	
	//특정 년,월,주 차에 월요일 구하기

 	public static String getMonday(String yyyy,String mm, String wk){
 		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(yyyyMMdd);
 		Calendar c = Calendar.getInstance();
 		int y=Integer.parseInt(yyyy);
 		int m=Integer.parseInt(mm)-1;
 		int w=Integer.parseInt(wk);
 		c.set(Calendar.YEAR,y);
 		c.set(Calendar.MONTH,m);
 		c.set(Calendar.WEEK_OF_MONTH,w);
 		c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
 		return formatter.format(c.getTime());
 	}

 	

 	//특정 년,월,주 차에 일요일 구하기
 	public static String getSunday(String yyyy,String mm, String wk){
 		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(yyyyMMdd);
 		Calendar c = Calendar.getInstance();
 		int y=Integer.parseInt(yyyy);
 		int m=Integer.parseInt(mm)-1;
 		int w=Integer.parseInt(wk);
 		c.set(Calendar.YEAR,y);
 		c.set(Calendar.MONTH,m);
 		c.set(Calendar.WEEK_OF_MONTH,w);
 		c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
 		c.add(c.DATE,7);
 		return formatter.format(c.getTime());

 	}
 	
 	public static String getYesterday(String dateFormat){
 		Calendar cal = Calendar.getInstance();
 	    cal.add(Calendar.DATE, -1);
 	    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, locale);
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
		return sdf.format(cal.getTime());
 	}
 	
 	public static String getAddOneDateOrMonth(String type, String date) {
 		String resultDate = "";
 		try {
 			if("WEEK".equals(type)) {
 				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
 				Calendar calendar = Calendar.getInstance();
 				calendar.setTime(sdf.parse(date));
 				calendar.add(Calendar.DATE, 1);
 				resultDate = sdf.format(calendar.getTime());
 			} else if("DAY".equals(type)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(sdf.parse(date));
				calendar.add(Calendar.DATE, 1);
				resultDate = sdf.format(calendar.getTime());
			} else if("MONTH".equals(type)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(sdf.parse(date));
				calendar.add(Calendar.MONTH, 1);
				resultDate = sdf.format(calendar.getTime());
			} else if("YEAR".equals(type)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(sdf.parse(date));
				calendar.add(Calendar.YEAR, 1);
				resultDate = sdf.format(calendar.getTime());
			}
 		} catch (Exception e) {
 			System.err.println(e);
 		}
 		return resultDate;
 	}
 	
	public static String getCustomDate(String yyyymmdd,String type,int num ) {

		int year = Integer.parseInt(yyyymmdd.substring(0, 4));
		int month = Integer.parseInt(yyyymmdd.substring(4, 6));
		int date = Integer.parseInt(yyyymmdd.substring(6, 8));
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date);
		if(type.equals("YEAR")){
			cal.add(Calendar.YEAR, -num); 
		}else if(type.equals("MONTH")){
			cal.add(Calendar.MONTH, -num); 
		}else if(type.equals("DAY")){
			cal.add(Calendar.DATE, -num); 
		}else{
			//Nothing
		}
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
		String r = dateFormatter.format(cal.getTime());
		return r;
	}



}
