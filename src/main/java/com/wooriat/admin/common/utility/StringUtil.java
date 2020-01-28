package com.wooriat.admin.common.utility;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class StringUtil {

    @Autowired
    private static MessageSourceAccessor message;

    public void setMessageSourceAccessor(MessageSourceAccessor message) {
        StringUtil.message = message;

    }

    /***
     * 숫자체크
     * <p>
     * 
     * @param num
     * @return
     */
    public static boolean chkNum(String num) {

        boolean chk = true;

        if (num == null)
            num = "";

        Pattern patt = Pattern.compile("^[0-9]*[0-9]$");
        Matcher match = patt.matcher(num);

        if (num == null || !match.find()) {
            chk = false;
        } else {
            chk = true;
        }

        return chk;
    }

    /**
     * 오늘날짜, 시간 가져오기 (Format : YYYYMMDD, HH24MISS) type "D" : 날짜 type "T" : 시간
     * (SECOND) type "F_A" : 양식있는 날짜 + 시간 type "F_MS" : 양식있는 날짜 + 시간
     * (MILLISECOND) type "MS" : 날짜 + 시간 (MILLISECOND) type "M" : 날짜 + 시간
     * (MINUTE) other type : 날짜 + 시간 (SECOND)
     * 
     * @param String
     *            type <code>날짜를 가져올지 시간을 가져올지</code>
     * @return String
     */
    public static String getToDay(String type) {
        Calendar now = Calendar.getInstance();
        String year = null;
        String month = null;
        String day = null;
        String hour = null;
        String minute = null;
        String second = null;
        String millisecond = null;

        year = String.valueOf(now.get(Calendar.YEAR));
        month = String.valueOf((now.get(Calendar.MONTH) + 1));
        day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
        hour = String.valueOf(now.get(Calendar.HOUR_OF_DAY));
        minute = String.valueOf(now.get(Calendar.MINUTE));
        second = String.valueOf(now.get(Calendar.SECOND));
        millisecond = String.valueOf(now.get(Calendar.MILLISECOND));

        if (month.length() == 1)
            month = "0" + month;
        if (day.length() == 1)
            day = "0" + day;
        if (hour.length() == 1)
            hour = "0" + hour;
        if (minute.length() == 1)
            minute = "0" + minute;
        if (second.length() == 1)
            second = "0" + second;

        if (type.equals("D")) {
            return year + month + day;
        } else if (type.equals("T")) {
            return hour + minute + second;
        } else if (type.equals("F_A")) {
            return year + "/" + month + "/" + day + " " + hour + ":" + minute + ":" + second;
        } else if (type.equals("F_MS")) {
            return year + "/" + month + "/" + day + " " + hour + ":" + minute + ":" + second + ":" + millisecond;
        } else if (type.equals("MS")) {
            return year + month + day + hour + minute + second + millisecond;
        } else if (type.equals("M")) {
            return year + month + day + hour + minute;
        } else {
            return year + month + day + hour + minute + second;
        }
    }

    public static String nextDate(String maxDate) {

        String rtn = "";
        try {

            if (maxDate.equals("none")) {
                return getToDay("M");
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = sdf.parse(maxDate);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MINUTE, 1);
            rtn = sdf.format(cal.getTime());
        } catch (ParseException e) {
            return getToDay("M");
        }

        return rtn;
    }

    public static String StringToDateTime(String date) {

        DateFormat format = new SimpleDateFormat("yyyymmdd");
        DateFormat toBeFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date toDate = null;
        String targetDate = null;
        try {
            toDate = format.parse(date);
            targetDate = toBeFormat.format(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return targetDate;
    }

    /**
     * array의 elements들을 separator를 구분자로 문자열 합치기
     * 
     * @IssueID :
     * @param array
     * @param separator
     * @author blackpet
     * @return
     */
    public static String join(Object[] array, String separator) {
        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < array.length; i++) {
            if (i > 0)
                buf.append(separator);

            buf.append(array[i].toString());
        }
        return buf.toString();
    }

    /**
     * src를 prefix와 postfix로 감싼다
     * 
     * @IssueID :
     * @param src
     * @param prefix
     * @param postfix
     * @author blackpet
     * @return
     */
    public static String wrap(String src, String prefix, String postfix) {
        return prefix + src + postfix;
    }

    /**
     * 줄바꿈 처리 \n 을
     * 
     * <pre>
     * <BR>
     * </pre>
     * 
     * 로 변경
     * 
     * @param str
     * @return
     * @throws Exception
     */
    public static String setHTML(String str) throws Exception {
        String returnStr = "";
        if (str == null)
            return "";

        returnStr = StringUtil.replace(str, "\n", "<BR>");
        returnStr = returnStr.replaceAll("'", "′");

        return returnStr;
    }

    /**
     * 줄바꿈 처리 \n 을 공백으로 변경
     * 
     * @param str
     * @return
     * @throws Exception
     */
    public static String setSpace(String str) throws Exception {
        if (str == null)
            return "";

        str = str.replace('\t', ' ');
        str = str.replace('\n', ' ');
        str = str.replace('\r', ' ');
        str = str.trim();

        return str;
    }

    /**
     * 디비에 들어갈때.. 에러방지 ' or -- 를 처리
     * 
     * @param str
     * @return
     * @throws Exception
     */
    public static String setDB(String str) throws Exception {
        String returnStr = "";
        if (str == null)
            return "";

        returnStr = StringUtil.replace(str, "'", "''");
        returnStr = StringUtil.replace(returnStr, "--", "- ");
        returnStr = returnStr.trim();

        return returnStr;
    }

    /**
     * 대상 String이 null일 경우 ""을, null이 아닐 경우 대상 String을 return
     * 
     * @param str
     *            대상 스트링
     */
    public static String nvl(String str) {
        if (str == null)
            return "";
        else
            return str;
    }

    /**
     * 대상 String이 null일 경우 "-"을, null이 아닐 경우 대상 String을 return
     * 
     * @param str
     *            대상 스트링
     */
    public static String nvlToDash(String str) {
        if (str == null)
            return "-";
        else if ("".equals(str))
            return "-";
        else
            return str;
    }

    /**
     * <pre>
     * 입력된 값이 널이면, "" 값으로 대체하고, 널이 아니면 입력값을 trim()후 리턴한다.
     * 
     * @param str 문자열
     * @return String 체크된 문자열
     * </pre>
     */
    public static String nvlt(String str) {
        if (str == null)
            return "";
        return str.trim();
    }

    /**
     * 대상 String이 null일 경우 ""을, null이 아닐 경우 대상 String을 trim한 후 return
     * 
     * @param str
     *            trim한 대상 스트링
     */
    public static String trim(String str) throws Exception {
        String sTmp = str;

        if (str == null) {
            sTmp = "";
        } else if (!"".equals(str) && str.length() > 0) {
            sTmp = str.trim();
        }

        return sTmp;
    }

    /**
     * 대상 String이 null일 경우 ""을, null이 아닐 경우 대상 String을 trim한 후 return
     * 
     * @param str
     *            trim한 대상 스트링
     * @param value
     *            null일 경우 대체 string
     */
    public static String trim(String str, String value) throws Exception {
        String sTmp = str;

        if (str == null) {
            sTmp = value;
        } else if (!"".equals(str) && str.length() > 0) {
            sTmp = str.trim();
        }

        return sTmp;
    }

    /**
     * 대상 String배열의 값이 null일 경우 ""를, null이 아닐 경우 원래값 그대로를 가진 String 배열을 return
     * 
     * @param str
     *            대상 스트링
     */
    public static String[] nvl(String str[]) throws Exception {
        String[] new_str = null;

        new_str = new String[str.length];
        for (int i = 0; i < str.length; i++)
            new_str[i] = nvl(str[i]);

        return new_str;
    }

    /**
     * 대상 String이 null일 경우 대체 String을, null이 아닐 경우 대상 String을 return
     * 
     * @param str
     *            대상 문자
     * @param val
     *            null일 경우 대체될 문자
     */
    public static String nvl(String str, String val) {
        if (str == null)
            return val;
        else if ("".equals(str))
            return val;
        else if ("null".equals(str))
            return val;
        else if (str.trim().length() == 0)
            return val;
        else
            return str;
    }

    /**
     * 대상 String(str1)이 String(str2)일 경우 대체 String(val1)을, 아닐 경우 String(val2)을
     * return
     * 
     * @param str
     *            대상 문자
     * @param val
     *            null일 경우 대체될 문자
     */
    public static String snvl(String str1, String str2, String val1, String val2) throws Exception {

        if (nvl(str1).equals(str2))
            return val1;
        else
            return val2;
    }

    /**
     * 대상 String(str1)이 String(str2)일 경우 대체 String(val1)을, 아닐 경우 String(str1)을
     * return
     * 
     * @param str
     *            대상 문자
     * @param val
     *            ""일 경우 대체될 문자
     */
    public static String snvl(String str1, String str2, String val1) throws Exception {
        if (nvl(str1).equals(str2))
            return val1;
        else
            return str1;
    }

    /**
     * 대상 String이 null일 경우 대체 int 를, null이 아닐 경우 대상 String을 int 로 변환하여 return
     * 
     * @param str
     *            대상 문자
     * @param val
     *            null일 경우 대체될 문자
     */
    public static int nvl(String str, int val) throws Exception {
        int iRs = 0;

        if (str == null)
            iRs = val;
        else
            iRs = Integer.parseInt(str);

        return iRs;

    }

    /**
     * 대상 int가 0일경우 경우 ""을 return
     * 
     * @param str
     *            대상 스트링
     */
    public static String intToNull(int iNo) throws Exception {
        if (iNo == 0)
            return "";
        else
            return Integer.toString(iNo);
    }

    /**
     * 대상 double가 0일경우 경우 ""을 return
     * 
     * @param str
     *            대상 스트링
     */
    public static String doubleToNull(double iNo) throws Exception {
        if (iNo == 0)
            return "";
        else
            return Double.toString(iNo);
    }

    /**
     * 대상 String 중 특정한 문자가 몇번 들어가 있는지 return
     * 
     * @param str
     *            대상 String
     * @param find
     *            찾고자 하는 String
     */
    public static int cntInStr(String str, String find) throws Exception {
        int i = 0;
        int pos = 0;
        while (true) {
            pos = str.indexOf(find, pos);
            if (pos == -1)
                break;
            i++;
            pos++;
        }

        return i;
    }

    /**
     * String 중 start위치 부터 count만큼 잘라내서 return 주의점-> start 는 1 base 가 아니고 0 base
     * 
     * @param str
     *            대상 문자열
     * @param start
     *            시작 index
     * @param count
     *            잘라올 character 수
     */
    public static String midString(String str, int start, int count) throws Exception {
        if (str == null)
            return null;

        String result = null;

        if (start >= str.length())
            result = "";
        else if (str.length() < start + count)
            result = str.substring(start, str.length());
        else
            result = str.substring(start, start + count);

        return result;
    }

    /**
     * 대상 String 의 뒤에서부터 count만큼 잘라내서 return
     * 
     * @param str
     *            대상 문자열
     * @param count
     *            잘라낼 character 수
     */
    public static String rightString(String str, int count) throws Exception {
        if (str == null)
            return null;

        String result = null;

        if (count == 0) // 갯수가 0 이면 공백을
            result = "";
        else if (count > str.length()) // 문자열 길이보다 크면 문자열 전체를
            result = str;
        else
            result = str.substring(str.length() - count, str.length());
        // 오른쪽 count 만큼 리턴

        return result;
    }

    /**
     * 대상 String 에서 특정 String을 찾아서 다른 String으로 대체하여 return
     * 
     * @param str
     *            대상 String
     * @param from
     *            찾는 String
     * @param to
     *            취환할 String
     */
    public static String replace(String str, String from, String to) {
        String sResult = "";
        try {
            if (str == null || str.length() == 0 || from == null || from.length() == 0 || to == null || to.length() == 0)
                return str;

            StringBuffer sb = null;

            sb = new StringBuffer(str.length() * 2);
            String posString = str.toLowerCase();
            String cmpString = from.toLowerCase();
            int i = 0;
            boolean done = false;
            while (i < str.length() && !done) {
                int start = posString.indexOf(cmpString, i);
                if (start == -1) {
                    done = true;
                } else {
                    sb.append(str.substring(i, start) + to);
                    i = start + from.length();
                }
            }
            if (i < str.length()) {
                sb.append(str.substring(i));
            }

            sResult = sb.toString();
        } catch (Exception e) {
            sResult = str;
        } finally {

        }

        return sResult;
    }

    /**
     * 대상 String 에서 < 와 > & 를 &lt; 와 &gt; 와 &amp;로 바꿔서 return
     * 
     * @param sHTML
     *            대상 String
     */
    public static String replaceHtmlTag(String sHTML) {
        if (sHTML == null || sHTML.trim().equals(""))
            return "";

        String sResult = "";
        StringBuffer sbResult = null;

        try {
            String s = "";
            sbResult = new StringBuffer();

            for (int i = 0; i < sHTML.length(); i++) {
                s = sHTML.substring(i, i + 1);

                if (s.equals("<")) {
                    sbResult.append("&lt;");
                } else if (s.equals(">")) {
                    sbResult.append("&gt;");
                } else if (s.equals("\"")) {
                    sbResult.append("&quot;");
                } else if (s.equals("'")) {
                    sbResult.append("&#39;");
                } else if (s.equals("&")) {
                    sbResult.append("&amp;");
                } else {
                    sbResult.append(s);
                }
            }

            sResult = sbResult.toString();
        } finally {
            sbResult = null;
        }
        sResult = replace(sResult, "/*", "");
        sResult = replace(sResult, "*/", "");
        sResult = replace(sResult, "%", "");
        sResult = replace(sResult, "%00", "");
        return sResult;
    }

    /**
     * 대상 String 을 trim 처리 한 후 < 와 > & 를 &lt; 와 &gt; 와 &amp;로 바꿔서 return
     * 
     * @param sHTML
     *            대상 String
     */
    public static String replaceHtmlTagTrim(String sHTML) throws Exception {
        return replaceHtmlTag(trim(sHTML));
    }

    /**
     * 대상 String 에서 ' 를 &#39; 로 바꿔서 return
     * 
     * @param str
     *            대상 String
     */
    public static String replaceQuote(String str) throws Exception {
        return nvl(replace(str, "'", " &#39; "));
    }

    /**
     * 대상 String 에서 " 를 &quot; 로 바꿔서 return
     * 
     * @param str
     *            대상 String
     */
    public static String replaceDQuote(String str) throws Exception {
        return nvl(replace(str, "\"", " &quot; "));
    }

    /**
     * 대상 String 에서 \r\n 을 <BR>
     * 로 바꿔서 return
     * 
     * @param str
     *            대상 String
     */
    public static String newlineToBr(String str) throws Exception {
        if (str == null || "".equals(str))
            return "&nbsp;";
        return replace(str, "\r\n", "<BR>");
    }

    /**
     * 대상 String 에서 \r\n 을 \\r\\n 으로 바꿔서 return
     * 
     * @param str
     *            대상 String
     */
    public static String newlineToChar(String str) throws Exception {
        if (str == null || "".equals(str))
            return "&nbsp;";
        return replace(str, "\r\n", "\\r\\n");
    }

    /**
     * 대상 String 앞에 공백을 삽입하여 원하는 길이의 String을 return
     * 
     * @param str
     *            대상 String
     * @param len
     *            원하는 String의 길이
     */
    public static String LeftBlanks(String str, int len) throws Exception {
        int i;
        StringBuffer buffer = new StringBuffer(str.trim());
        int buffLen = buffer.length();
        if (buffLen < len) {
            for (i = buffLen; i < len; i++) {
                buffer.insert(0, " ");
            }
        }

        return buffer.toString();
    }

    /**
     * 대상 int 를 String으로 변환하여 앞에 0 을 삽입하여 원하는 길이의 String을 return
     * 
     * @param in
     *            대상 int
     * @param len
     *            원하는 String의 길이
     */
    public static String LeftZeros(int in, int len) throws Exception {
        String line = Integer.toString(in);
        int i;
        StringBuffer buffer = new StringBuffer(line.trim());
        int buffLen = buffer.length();
        if (buffLen < len) {
            for (i = buffLen; i < len; i++) {
                buffer.insert(0, "0");
            }
        }

        return buffer.toString();
    }

    /**
     * 대상 String을 int로 변환하여 return
     * 
     * @param s
     *            대상 String
     */
    public static int toInt(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        } else {
            return Integer.parseInt(s);
        }
    }

    /**
     * 대상 int를 String 으로 변환하여 return
     * 
     * @param i
     *            대상 int
     */
    public static String toString(int i) throws Exception {
        return String.valueOf(i);
    }

    /**
     * 한글 포함한 스트링의 정확한 length 구하는 메소드
     * 
     * @param s
     *            한글 포함한 스트링
     * @return length
     */
    public static int getLength(String s) throws Exception {
        int strlen = 0;

        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (c < 0xac00 || 0xd7a3 < c)
                strlen++;
            else
                strlen += 2; // 한글
        }
        return strlen;
    }

    /**
     * 문자가 길경우에 특정 바이트 단위 길이로 자른다.
     * 
     * @param str
     *            문자열
     * @param byteSize
     *            남길 문자열의 길이
     * @return string 자르고 남은 문자열
     * @throws Exception
     */
    public static String getTitleOfList(String str, int byteSize) throws Exception {
        int rSize = 0;
        int len = 0;

        if (str.getBytes().length > byteSize) {
            for (; rSize < str.length(); rSize++) {
                if (str.charAt(rSize) > 0x007F)
                    len += 2;
                else
                    len++;

                if (len > byteSize)
                    break;
            }
            str = str.substring(0, rSize) + "...";
        }

        return str;

    }

    /**
     * 문자가 길경우에 특정 위치에서 <BR>
     * 을 넣어 준다.
     * 
     * @param str
     *            문자열
     * @param byteSize
     *            남길 문자열의 길이
     * @return string 자르고 남은 문자열
     * @throws Exception
     */
    public static String cutTextSize(String str, int byteSize) throws Exception {
        String ret = "";

        if (str.length() > byteSize) {
            int length = str.length() / byteSize;
            for (int i = 0; i < length; i++) {
                if (str.length() % byteSize == 0 && i == length - 1) {
                    ret = ret + str.substring(0, byteSize);
                } else {
                    ret = ret + str.substring(0, byteSize) + "<br>";
                }
                str = str.substring(byteSize);

                if (i == length - 1 && str.length() < byteSize) {
                    ret = ret + str;
                }
            }

        } else
            ret = str;
        return ret;

    }

    /**
     * 한글 포함한 스트링의 정확한 substring
     * 
     * @param s
     *            한글 포함한 스트링
     * @param begin
     *            begin
     * @param end
     *            end
     * @return 한글 포함한 substring
     */
    public static String getSubString(String s, int begin, int end) throws Exception {
        int rlen = 0;
        int sbegin = 0;
        int send = 0;

        for (sbegin = 0; sbegin < s.length(); sbegin++) {
            if (s.charAt(sbegin) > 0x007f) {
                rlen += 2;
                if (rlen > begin) {
                    rlen -= 2;
                    break;
                }
            } else {
                rlen++;
                if (rlen > begin) {
                    rlen--;
                    break;
                }
            }
        }

        for (send = sbegin; send < s.length(); send++) {
            if (s.charAt(send) > 0x007f) {
                rlen += 2;
            } else {
                rlen++;
            }

            if (rlen > end)
                break;
        }

        return s.substring(sbegin, send);
    }

    /**
     * 한글 포함한 스트링의 정확한 substring 글자 수 초과시 ... 을 붙임
     * 
     * @param s
     *            한글 포함한 스트링
     * @param begin
     *            begin byte
     * @param end
     *            end byte
     * @return 한글 포함한 substring
     */
    public static String getSubStringAddDot(String s, int begin, int end) throws Exception {
        int rlen = 0;
        int sbegin = 0;
        int send = 0;
        String returnString = "";

        for (sbegin = 0; sbegin < s.length(); sbegin++) {
            if (s.charAt(sbegin) > 0x007f) {
                rlen += 2;
                if (rlen > begin) {
                    rlen -= 2;
                    break;
                }
            } else {
                rlen++;
                if (rlen > begin) {
                    rlen--;
                    break;
                }
            }
        }

        for (send = sbegin; send < s.length(); send++) {
            if (s.charAt(send) > 0x007f) {
                rlen += 2;
            } else {
                rlen++;
            }

            if (rlen > end)
                break;
        }

        returnString = s.substring(sbegin, send);

        if (rlen > end)
            returnString = returnString + "...";

        return returnString;
    }

    /**
     * StringTokenizer를 이용해서 원하는 구분자로 대상 String을 잘라서 일차원 String 배열로 리턴
     * 
     * @param pStr
     *            대상 String
     * @param pDelim
     *            구분자 String
     * @return 구분자로 나눠진 String 배열
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static String[] splitString(String pStr, String pDelim) throws Exception {
        if (pStr == null || pStr.length() == 0 || pDelim == null || pDelim.length() == 0) {
            return null;
        }

        String[] results = null;

        Vector vec = null;

        StringTokenizer stz = null;

        try {
            vec = new Vector();

            stz = new StringTokenizer(pStr, pDelim);

            while (stz.hasMoreElements()) {
                String temp = stz.nextToken();

                vec.addElement(temp);
            }

            results = new String[vec.size()];
            for (int i = 0; i < results.length; i++) {
                results[i] = (String) vec.elementAt(i);
            }
        } finally {
            vec = null;
            stz = null;
        }

        return results;
    }

    /**
     * 대상 String 내에 특정 String 패턴을 HTML태그로 강조
     * 
     * @param pString
     *            대상 String
     * @param pPattern
     *            강조할 String 패턴
     * @return 강조된 String
     * @throws Exception
     */
    public static String getHighLighting(String pString, String pPattern) throws Exception {
        if (pString == null || pString.length() == 0 || pPattern == null || pPattern.length() == 0)
            return pString;

        String[] somePatterns = null;
        try {
            somePatterns = splitString(pPattern, " ");

            for (int i = 0; i < somePatterns.length; i++) {
                // System.out.println(somePatterns[i]);
                pString = replace(pString, somePatterns[i], "<FONT COLOR=\"red\">" + somePatterns[i] + "</FONT>");
            }
        } finally {
            somePatterns = null;
        }

        return pString;
    }

    /**
     * 제목을 보여줄때 제한된 길이를 초과하면 뒷부분을 짜르고 "..." 으로 대치한다.
     * 
     * @param str
     *            대상 String
     * @param len
     *            나타낼 길이
     * @return ..추가된 String
     * @throws Exception
     */
    public static String formatTitle(String strTarget, int iLimit) throws Exception {

        if (strTarget == null || strTarget.equals(""))
            return "";

        int rSize = 0;
        int len = 0;

        if (strTarget.getBytes().length > iLimit) {
            for (; rSize < strTarget.length(); rSize++) {
                if (strTarget.charAt(rSize) > 0x007F)
                    len += 2;
                else
                    len++;

                if (len > iLimit)
                    break;
            }
            strTarget = strTarget.substring(0, rSize) + "...";
        }

        return strTarget;
    }

    /**
     * 제목을 보여줄때 제한된 길이를 초과하면 뒷부분을 짜르고 "..." 으로 대치한다.
     * 
     * @param str
     *            대상 String
     * @param len
     *            나타낼 길이
     * @return ..추가된 String
     * @throws Exception
     */
    public static String formatTitle2(String strTarget, int iLimit) throws Exception {

        if (strTarget == null || strTarget.equals(""))
            return "";
        if (strTarget.length() > iLimit) {
            strTarget = strTarget.substring(0, iLimit) + "...";
        }
        return strTarget;
    }

    /**
     * 태그 제거하기
     * 
     * 사용 예: String str =
     * "<BODY>Hello <FONT FACE=\"궁서\"><B>진\"하게\"</B> 쓰>기</FONT></BODY>";
     * System.out.println(removeTag(str));
     * 
     * 결과: 진\"하게\" 쓰>기
     * 
     * @param s
     *            tag포함된 문장
     * @return String tag제거된 문장
     */
    public static String getRemoveTag(String s) throws Exception {

        if (s == null)
            return "";

        final int iNormalState = 0;
        final int iTagState = 1;
        final int iStartTagState = 2;
        final int iEndiTagState = 3;
        final int iSingleQuotState = 4;
        final int iDoubleQuotState = 5;
        int state = iNormalState;
        int oldState = iNormalState;
        char[] chars = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        char a;
        for (int i = 0; i < chars.length; i++) {
            a = chars[i];
            switch (state) {
            case iNormalState:
                if (a == '<')
                    state = iTagState;
                else
                    sb.append(a);
                break;
            case iTagState:
                if (a == '>')
                    state = iNormalState;
                else if (a == '\"') {
                    oldState = state;
                    state = iDoubleQuotState;
                } else if (a == '\'') {
                    oldState = state;
                    state = iSingleQuotState;
                } else if (a == '/')
                    state = iEndiTagState;
                else if (a > 0x007f) { // < 테크 다음에 한글인 경우
                    sb.append('<');
                    sb.append(a);
                    state = iNormalState;
                } else if (a != ' ' && a != '\t' && a != '\n' && a != '\r' && a != '\f')
                    state = iStartTagState;
                break;
            case iStartTagState:
            case iEndiTagState:
                if (a == '>')
                    state = iNormalState;
                else if (a == '\"') {
                    oldState = state;
                    state = iDoubleQuotState;
                } else if (a == '\'') {
                    oldState = state;
                    state = iSingleQuotState;
                } else if (a == '\"')
                    state = iDoubleQuotState;
                else if (a == '\'')
                    state = iSingleQuotState;
                break;
            case iDoubleQuotState:
                if (a == '\"')
                    state = oldState;
                break;
            case iSingleQuotState:
                if (a == '\'')
                    state = oldState;
                break;
            }
        }
        return sb.toString();
    }

    /**
     * 도메인에서 HTTP 자르기
     * 
     * @param in
     * @return http제거된 url
     */
    public static String cutHTTP(String in) {
        if (in == null)
            return null;
        if (in.length() < 4)
            return in;

        if (in.substring(0, 7).equals("http://")) {
            in = in.substring(7);
            in = in.substring(in.indexOf("/"), in.length());
        }
        return in;
    }

    /**
     * " : " 구분자로 들어오는 입력값을 따로 나누는 메소드
     * 
     * @param input
     * @return
     */
    public static String split1(String input) {
        // null체크
        if (input == null) {
            return null;
        }
        if (input.equals("")) {
            return null;
        }

        String result = new String();
        int part = 0;

        part = input.indexOf(":");
        result = input.substring(0, part);
        return result;
    }

    /**
     * " : " 구분자로 들어오는 입력값을 따로 나누는 메소드
     * 
     * @param input
     * @return
     */
    public static String split2(String input) {
        // null체크
        if (input == null) {
            return null;
        }
        if (input.equals("")) {
            return null;
        }

        String result = new String();
        int part = 0, all = 0;

        part = input.indexOf(":");
        all = input.length();
        result = input.substring(part + 1, all);

        return result;
    }

    /**
     * " : " 구분자로 들어오는 입력값을 따로 나누는 메소드
     * 
     * @param input
     * @return
     */
    public static String[] split3(String input) {
        // null체크
        if (input == null) {
            return null;
        }
        if (input.equals("")) {
            return null;
        }

        String[] result = new String[5];
        int part = 0;

        for (int i = 0; i < 4; i++) {
            part = input.indexOf(":");
            result[i] = input.substring(0, part);
            input = input.substring(part + 1);
        }
        result[4] = input;
        return result;
    }

    /**
     * 년,월,일,시,분등과 관련된 HTML <option> 을 출력한다. default 값을 주면 그 값이 선택되게 한다.
     * 
     * @param start
     * @param end
     * @param nDefault
     * @return
     */
    public static String getDateOptions(int start, int end, int nDefault) {
        String result = "";

        for (int i = start; i <= end; i++) {
            if (i < 100) {
                String temp = String.valueOf(i + 100);

                temp = temp.substring(1);

                if (i == nDefault) {
                    result += "<option value=\"" + temp + "\" selected>" + temp;
                } else {
                    result += "<option value=\"" + temp + "\">" + temp;
                }
            } else {
                if (i == nDefault) {
                    result += "<option value=\"" + i + "\" selected>" + i;
                } else {
                    result += "<option value=\"" + i + "\">" + i;
                }
            }
        }
        return result;
    }

    /**
     * 숫자값을 콤마 처리 후 결과값 리턴 - 숫자형 Argement
     * 
     * @param ps_val
     * @return
     */
    public static String setComma(String ps_val) {
        long l_val = 0;
        if ((ps_val == null) || (ps_val.length() == 0))
            return "0";

        l_val = Long.parseLong(ps_val);
        java.text.NumberFormat s_fmt = java.text.NumberFormat.getInstance();
        String s_tmp = s_fmt.format(l_val);
        return s_tmp;
    }

    /**
     * 숫자값을 콤마 처리 후 결과값 리턴 - Long형 Argement
     * 
     * @param pl_val
     * @return
     */
    public static String setComma(long pl_val) {
        java.text.NumberFormat s_fmt = java.text.NumberFormat.getInstance();
        String s_tmp = s_fmt.format(pl_val);
        return s_tmp;
    }

    /**
     * 숫자값을 콤마 처리 후 결과값 리턴 - double Argement
     * 
     * @param pl_val
     * @return
     */
    public static String setComma(double pl_val) {
        java.text.NumberFormat s_fmt = java.text.NumberFormat.getInstance();
        String s_tmp = s_fmt.format(pl_val);
        return s_tmp;
    }

    /**
     * subMoney
     * 
     * @param pl_val
     * @return String
     */
    public static String subMoney(long pl_val) {
        String sl_val = "";
        sl_val = Long.toString(pl_val);

        if (!"".equals(sl_val)) {
            if (sl_val.length() > 3) {
                sl_val = sl_val.substring(0, sl_val.length() - 3);
            }
        }

        java.text.NumberFormat s_fmt = java.text.NumberFormat.getInstance();
        String s_tmp = s_fmt.format(Long.parseLong(sl_val));
        return s_tmp;
    }

    /**
     * subMoney
     * 
     * @param pl_val
     * @return String
     */
    public static String subMoney(double pl_val) {
        String sl_val = "";
        sl_val = Double.toString(pl_val);

        if (!"".equals(sl_val)) {
            if (sl_val.length() > 3) {
                sl_val = sl_val.substring(0, sl_val.length() - 3);
            }
        }

        java.text.NumberFormat s_fmt = java.text.NumberFormat.getInstance();
        String s_tmp = s_fmt.format(Long.parseLong(sl_val));
        return s_tmp;
    }

    /**
     * 문자열에서 콤마제거 후 결과값 리턴
     * 
     * @param ps_val
     * @return
     */
    public static String getDeleteComma(String ps_val) {
        StringTokenizer s_tok_str = new StringTokenizer(ps_val, ",");
        String s_con_str = "";
        while (s_tok_str.hasMoreTokens()) {
            String s_tmp = s_tok_str.nextToken();
            s_con_str = s_con_str + s_tmp;
        }
        return s_con_str.trim();
    }

    /**
     * 문자열에서 특정문자 제거후 리턴
     * 
     * @param ps_val
     * @param chrs
     * @return
     */
    public static String getDeleteChar(String ps_val, String chrs) {
        StringTokenizer s_tok_str = new StringTokenizer(ps_val, chrs);
        String s_con_str = "";
        while (s_tok_str.hasMoreTokens()) {
            String s_tmp = s_tok_str.nextToken();
            s_con_str = s_con_str + s_tmp;
        }
        return s_con_str.trim();
    }

    /**
     * formatMoney
     * 
     * @param str
     * @return String
     */
    public static String formatMoney(String str) {
        if (str == null || "".equals(str)) {

        } else {
            double iAmount = (new Double(str)).doubleValue();
            java.text.DecimalFormat df = new java.text.DecimalFormat("###,###,###,###,###,###,###");
            return df.format(iAmount);
        }

        return "0";
    }

    /**
     * 문자열을 특정 크기로 만듬, 만약 남는 공간이 있으면 왼쪽에서부터 특정문자(cSpace)를 채움<BR>
     * null이 입력되더라도 크기 만큼 특정문자를 채움
     * 
     * @param strText
     *            String 문자열
     * @param cSpace
     *            char 빈공란에 채울 특정문자
     * @param iTotalSize
     *            int 특정 크기
     * @param bIsLeft
     *            왼쪽채우기
     * @return 변경된 문자열
     */
    public static String fixTextSize(String strText, char cSpace, int iTotalSize, boolean bIsLeft) {

        if (strText == null) {
            strText = "";
        }

        if (strText.length() < iTotalSize) {

            // 문자열의 크기가 특정크기보다 작을 때는 특정문자로 채움
            char[] carraySpace = new char[iTotalSize - strText.length()];
            Arrays.fill(carraySpace, cSpace);

            String strSpace = new String(carraySpace);

            if (bIsLeft) {
                // 왼쪽으로 공백을 채움
                return strSpace + strText;
            } else {
                // 오른쪽으로 공백을 채움
                return strText + strSpace;
            }

        } else {

            // 문자열의 크기가 특정크기보다 클때는 앞쪽의 문자열 잘라냄
            return strText.substring(strText.length() - iTotalSize, strText.length());

        }

    }

    public static String printRegisterNo(String registno) {
        String rtnStr = "";
        if (registno.length() >= 6) {
            rtnStr = registno.substring(0, 6) + "-XXXXXXX";
        }
        return rtnStr;
    }

    /**
     * 데이타베이스에 저장하기전 문자열 치환
     * 
     * @param srcStr
     *            데이타베이스에 저장하기전 변경할 문자열
     * @return 치환된 문자열
     */
    public static String putReplaceStr(String srcStr) {
        if (srcStr != null) {
            srcStr = srcStr.replace('\'', '');
            srcStr = srcStr.replace(',', '');
        }
        return srcStr;
    }

    /**
     * 문자열 치환하기 양수용 추가 *
     * 
     * @param src
     *            String
     * @param oldstr
     *            String
     * @param newstr
     *            String
     * @return String
     * @throws Exception
     */
    public static String str_replace(String src, String oldstr, String newstr) throws Exception {
        return str_replace(src, oldstr, newstr, false);
    }

    /**
     * 대소문자 상관없이 문자열 치환하기 양수용 추가 *
     * 
     * @param src
     *            String
     * @param oldstr
     *            String
     * @param newstr
     *            String
     * @param ignorecase
     *            boolean
     * @return String
     * @throws Exception
     */
    public static String str_replace(String src, String oldstr, String newstr, boolean ignorecase) throws Exception {
        return str_replace(src, oldstr, newstr, ignorecase, false);
    }

    /**
     * 대소문자 상관없이 문자열 치환하기 양수용 추가 *
     * 
     * @param src
     *            String
     * @param oldstr
     *            String
     * @param newstr
     *            String
     * @param ignorecase
     *            boolean
     * @return String
     * @throws Exception
     */
    public static String str_replace(String src, String oldstr, String newstr, boolean ignorecase, boolean firstonly) throws Exception {
        if (src == null)
            return null;

        StringBuffer dest = new StringBuffer("");
        int len = oldstr.length();
        int srclen = src.length();
        int pos = 0;
        int oldpos = 0;

        if (ignorecase) {
            while ((pos = indexOfi(src, oldstr, oldpos)) >= 0) {
                dest.append(src.substring(oldpos, pos));
                dest.append(newstr);
                oldpos = pos + len;
                if (firstonly)
                    break;
            }
        } else {
            while ((pos = src.indexOf(oldstr, oldpos)) >= 0) {
                dest.append(src.substring(oldpos, pos));
                dest.append(newstr);
                oldpos = pos + len;
                if (firstonly)
                    break;
            }
        }

        if (oldpos < srclen)
            dest.append(src.substring(oldpos, srclen));

        return dest.toString();
    }

    /**
     * 대소문자 상관없이 문자열 검색하기 양수용 추가 *
     * 
     * @param src
     *            String
     * @param find
     *            String
     * @param pos
     *            int
     * @return int
     * @throws Exception
     */
    public static int indexOfi(String src, String find, int pos) throws Exception { // 대소문자
                                                                                    // 상관안하고
                                                                                    // 스트링위치를
                                                                                    // 찾는
                                                                                    // 함수
        find = find.toLowerCase(); // 전부 소문자로..
        int src_len = src.length();
        int find_len = find.length();
        int src_pos = pos;
        int find_pos = 0;

        while (find_pos < find_len && src_pos < src_len) {
            char src_char = src.charAt(src_pos);
            if ((src_char >= 'A' && src_char <= 'Z') || (src_char >= 'a' && src_char <= 'z')) { // 알파벳
                                                                                                // 대역이면,
                if (src_char == find.charAt(find_pos) || src_char == find.charAt(find_pos) - 32)
                    find_pos++;
                else
                    find_pos = 0;
            } else {
                if (src_char == find.charAt(find_pos))
                    find_pos++;
                else
                    find_pos = 0;
            }
            src_pos++;
        }

        if (find_pos == find_len)
            return src_pos - find_len;
        else
            return -1;
    }

    /**********************************************************************************/

    /**
     * 태그 인젝션 처리후 반환
     * 
     * @param value
     * @param bContent
     * @return
     */
    public static String getTagInjection(String value, boolean bContent) {
        value = value.replaceAll("<", "&lt;");
        value = value.replaceAll(">", "&gt;");

        if (bContent) {
            value = value.replaceAll("&lt;p&gt;", "<p>");
            value = value.replaceAll("&lt;br&gt;", "<br>");
        }

        return value;
    }

    /**
     * sql 비유효성 문자 제거
     * 
     * @param value
     * @return
     */
    public static String getSqlInjection(String value) {
        if (value.indexOf(";") > 0) {
            value = getDeleteChar(value, ";");
        }

        if (value.indexOf(",") > 0) {
            value = getDeleteChar(value, ",");
        }

        if (value.indexOf("\'") > 0) {
            value = getDeleteChar(value, "\'");
        }

        if (value.indexOf("\"") > 0) {
            value = getDeleteChar(value, "\"");
        }

        if (value.indexOf("+") > 0) {
            value = getDeleteChar(value, "+");
        }

        return value;
    }

    /**
     * 파일명의 확장자를 검사하여 업로드 가능여부를 반환
     * 
     * @param FileName
     * @return
     */
    public static boolean IsAbleUploadFile(String FileName) {
        boolean result = true;

        String file_ext = FileName.substring(FileName.lastIndexOf('.') + 1);

        // 업로드 금지 파일
        if (!(file_ext.equalsIgnoreCase("hwp") || file_ext.equalsIgnoreCase("pdf") || file_ext.equalsIgnoreCase("jpg"))) {
            result = false;
        }

        return result;
    }

    /**
     * 의도적인 deserialization 를 금지하기 위해..클래스 내에 아래 메소드를 추가한다. private final void
     * readObject(ObjectInputStream in) throws java.io.IOException { throw new
     * java.io.IOException("Class cannot be deserialized");
     */

    public static int getStartIndex(int pageNumber, int rowCount) {

        int index = 0;
        index = rowCount * (pageNumber - 1);

        if (index < 0)
            index = 0;

        return index;
    }

    /**
     * 정수 7의 문자를 07, 007의 문자열로 치환 ex) zeroFill("000", 7) ===> 007
     * 
     * @param format
     * @param input
     * @return
     */
    public static String zeroFill(String format, String input) {
        DecimalFormat DF = new DecimalFormat(format);
        String outPut = DF.format(Integer.parseInt(input));
        return outPut;
    }

    public static String getImageTag(String content) {
        String contents = "";
        String imgSrcTag = "";
        String strTmp1 = "";
        String strTmp2 = "";
        int intTemp1 = 0;

        // contents = content.toUpperCase();
        contents = content;

        intTemp1 = contents.indexOf("<IMG");

        if (intTemp1 <= 5)
            intTemp1 = contents.indexOf("<img");

        if (intTemp1 > 5) {
            strTmp1 = contents.substring(intTemp1);
            strTmp2 = strTmp1.substring(strTmp1.indexOf("src="), strTmp1.indexOf(">")).replace("src=\"", "");
            imgSrcTag = strTmp2.substring(0, strTmp2.indexOf('"'));
        } else {
            imgSrcTag = "N";
        }
        return imgSrcTag;
    }

    // XSS 처리
    public static String cleanXSS(String str) {
        str = str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        str = str.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        str = str.replaceAll("'", "&#39;");
        str = str.replaceAll("eval\\((.*)\\)", "");
        str = str.replaceAll("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']", "\"\"");
        str = str.replaceAll("[\\\"\\'][\\s]*JAVASCRIPT:(.*)[\\\"\\']", "\"\"");
        str = str.replaceAll("&lt;/script&gt;", "");
        str = str.replaceAll("&lt;SCRIPT&gt;", "");
        str = str.replaceAll("&lt;/script&gt;", "");
        str = str.replaceAll("&lt;SCRIPT&gt;", "");
        return str;
    }

    // HTML태그
    public static String replaceTagtoHtml(String str) {
        if (str != null) {
            str = str.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
        }

        return str;
    }

    //
    public static String replaceInvaild(String str) {
        if (str != null) {
            // XSS 체크
            str = cleanXSS(str);
            return str;
        } else {
            return str;
        }
    }

    public static String checkSelect(String val1, String val2) {
        String selected = "";
        val1 = nvl(val1);
        val2 = nvl(val2);
        if (val1.equals("") && val2.equals("")) {
            return selected;
        }

        if (val1.equals(val2)) {
            selected = "selected";
        }

        return selected;
    }

    /**
     * 암호화 인코딩
     * 
     * @param String
     * @return String
     */
    public static String encoding(String pwd) throws Exception {
        int i = 0;
        int emp_no1 = 0;
        int emp_no2 = 0;
        int emp_no3 = 0;
        int result1 = 0;
        byte ch;
        String result = "";

        try {
            int ccc = pwd.length();
            for (i = 0; i < 3; i++) {
                ch = (byte) pwd.charAt(i);

                if (i == 0) {
                    switch (ch) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        emp_no1 = (int) ch;
                        break;
                    default:
                        emp_no1 = 4;
                        break;
                    }
                } else if (i == 1) {
                    switch (ch) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        emp_no2 = 5;
                        break;
                    default:
                        emp_no2 = 3;
                        break;
                    }
                } else if (i == 2) {
                    switch (ch) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        emp_no1 = (int) ch;
                        break;
                    default:
                        emp_no1 = 2;
                        break;
                    }
                }
            }

            for (i = 0; i < ccc; i++) {
                int aaa = 0;
                aaa = i % 6;
                char ddd = pwd.charAt(i);

                switch (aaa) {
                case 0:
                    result1 = (int) ddd - emp_no2;
                    result = result + (char) result1;
                    break;
                case 1:
                    result1 = (int) ddd - emp_no3;
                    result = result + (char) result1;
                    break;

                case 2:
                    result1 = (int) ddd - emp_no1;
                    result = result + (char) result1;
                    break;

                case 3:
                    result1 = (int) ddd - emp_no3 - emp_no2;
                    result = result + (char) result1;
                    break;
                case 4:
                    result1 = (int) ddd - emp_no1 - emp_no3;
                    result = result + (char) result1;
                    break;
                case 5:
                    result1 = (int) ddd - emp_no1 - emp_no2;
                    result = result + (char) result1;
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 암호화 디코딩
     * 
     * @param String
     * @return String
     */
    public static String decoding(String pwd) throws Exception {
        int i = 0;
        int emp_no1 = 0;
        int emp_no2 = 0;
        int emp_no3 = 0;
        int result1 = 0;
        byte ch;
        String result = "";

        try {
            int ccc = pwd.length();
            for (i = 0; i < 3; i++) {
                ch = (byte) pwd.charAt(i);

                if (i == 0) {
                    switch (ch) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        emp_no1 = (int) ch;
                        break;
                    default:
                        emp_no1 = 4;
                        break;
                    }
                } else if (i == 1) {
                    switch (ch) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        emp_no2 = 5;
                        break;
                    default:
                        emp_no2 = 3;
                        break;
                    }
                } else if (i == 2) {
                    switch (ch) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        emp_no1 = (int) ch;
                        break;
                    default:
                        emp_no1 = 2;
                        break;
                    }
                }
            }

            for (i = 0; i < ccc; i++) {
                int aaa = 0;
                aaa = i % 6;
                char ddd = pwd.charAt(i);

                switch (aaa) {
                case 0:
                    result1 = (int) ddd + emp_no2;
                    result = result + (char) result1;
                    break;
                case 1:
                    result1 = (int) ddd + emp_no3;
                    result = result + (char) result1;
                    break;

                case 2:
                    result1 = (int) ddd + emp_no1;
                    result = result + (char) result1;
                    break;

                case 3:
                    result1 = (int) ddd + emp_no3 + emp_no2;
                    result = result + (char) result1;
                    break;
                case 4:
                    result1 = (int) ddd + emp_no1 + emp_no3;
                    result = result + (char) result1;
                    break;
                case 5:
                    result1 = (int) ddd + emp_no1 + emp_no2;
                    result = result + (char) result1;
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 전화번호 MASK 처리
     * 
     * @IssueID :
     * @param array
     * @param separator
     * @author blackpet
     * @return
     */
    public static String getMaskCtn(String str) {
        String result = "";

        if (!"".equals(nvl(str))) {
            result = "XXX-XXXX-" + str.substring(str.length() - 4, str.length());
        }
        return result;
    }

    /**
     * 전화번호 MASK 처리(가운데 자리만 * 처리)
     * @param str
     * @return
     */
    public static String getMaskCtn2(String str){
    	String result = "";

    	if(!"".equals(nvl(str))) {
    		result = str.substring(0, 3) + "-****-" + str.substring(str.length() - 4, str.length());
    	}
    	return result;
    }
    
    
    /**
     * 주어진 문자열로 길이 만큼 문자열 앞에 채워넣는다.
     * 
     * @param str
     * @param pad
     * @param len
     * @return
     */
    public static String lpad(String str, String pad, int len) {
        String chStr = "";

        if (nvl(str).equals("")) {
            return "";
        }
        int strLenth = str.length();
        chStr = str;
        for (int i = strLenth; i < len; i++) {

            chStr = pad + chStr;
        }
        return chStr;
    }

    /**
     * 특정날짜의 요일 반환 ex) 1(일요일),2(월요일),3(화요일),4(수요일),5(목요일),6(금요일),7(토요일)
     * 
     * @param format
     * @return
     * @throws ParseException
     */
    public static int weekDay(String str) throws Exception {

        String inputDate = str;
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date myDate = df.parse(inputDate);
        Calendar cld = Calendar.getInstance();
        cld.setTime(myDate);

        return cld.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 오늘 날짜로부터 몇 년/달/일 전/후의 날짜 계산 ex) 오늘로부터 10개월 전 날짜를 구하라 => getDate("M", 10)
     * 
     * @param YMD
     *            : Y:년계산, M:월계산, D:일계산
     * @param term
     *            : +/- 년/월/일
     * @return 계산된 날짜 YYYYMMDD 형태(int)
     */
    @SuppressWarnings("static-access")
    public static int getDate(String YMD, int term) {
        int result = 0;
        Calendar now = Calendar.getInstance();

        if ("Y".equals(YMD)) {
            now.add(now.YEAR, term);
        } else if ("M".equals(YMD)) {
            now.add(now.MONTH, term);
        } else if ("D".equals(YMD)) {
            now.add(now.DAY_OF_MONTH, term);
        }

        String year = String.valueOf(now.get(Calendar.YEAR));
        String month = String.valueOf((now.get(Calendar.MONTH) + 1));
        String day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));

        if (month.length() == 1)
            month = "0" + month;
        if (day.length() == 1)
            day = "0" + day;

        result = Integer.parseInt(year + month + day);

        return result;
    }

    public static String replace_asterisk(String str) {
        if (str == null)
            return null;

        String result = null;

        if (str.length() > 2) {
            result = str.substring(0, 1) + "*" + str.substring(2, str.length());
        } else if (str.length() == 2) {
            result = str.substring(0, 1) + "*";
        } else {
            result = "*";
        }

        return result;
    }

    /**
     * XSS 관련 문자를 아스키코드값으로 변환
     * 
     * @param s
     *            변환할 문자열
     * @return 변환한 문자열
     */
    public static String convertCharToAscii(String s) {
        if (!isEmpty(s)) {
            s = s.replaceAll("&", "&amp;");
            s = s.replaceAll("<", "&lt;");
            s = s.replaceAll(">", "&gt;");
            s = s.replaceAll("'", "&#39;");
            s = s.replaceAll("\"", "&#34;");
        }

        return s;
    }
    
    /**
     * 공백이거나 널인 경우 true 반환
     * 
     * @param o
     *            검사할 객체
     * @return 공백이나 널인경우 true 아닌 경우는 false
     */
    public static boolean isEmpty(Object o) {
        if (o == null)
            return true;
        else if ("".equals(o))
            return true;
        else
            return false;
    }    
    
    /**
     * 파일 사이즈 (Byte -> MB로 변환)
     * 
     * @param size
     * @return
     */
    public static String getFileSize(int size) {
        double value = (double) size / (1024 * 1024);
        int dott = String.valueOf(value).indexOf(".");
        String temp = String.valueOf(value).substring(0, dott + 3);
        return temp;
    }

    public static String getPropMessage(String key) {

        return message.getMessage(key);
    }

}
