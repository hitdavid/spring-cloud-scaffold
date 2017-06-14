package com.hitdavid.service.util;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by David on 15/3/17.
 */
public class StringUtil {

    public static final String CHARSET_UTF = "UTF-8";
    public static final String CHARSET_GBK = "GBK";
    public static final String CHARSET_GB2312 = "GB2312";
    public static final String CHARSET_GB18030 = "GB18030";
    public static final String CHARSET_UNICODE = "UNICODE";
    public static final String CHARSET_ISO88591 = "ISO-8859-1";
    public static final int NEGATIVE_ONE = -1;



    public static final String EMPTY = "";
    public static final String ENGLISH_ELLIPSIS = "...";


    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     *
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj) {

        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();

        if (obj instanceof Map)
            return ((Map) obj).isEmpty();

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }

        return false;
    }

    /***
     * 校验手机号格式
     *
     * @param mobile
     * @return
     */
    public static boolean isMobileNumber(String mobile) {
        return Pattern.compile("^1\\d{10}$").matcher(mobile).matches();
    }

    public static String convertUTF2ISO(String oldName) {
        if (oldName == null) {
            return oldName;
        }
        try {
            return new String(oldName.getBytes(CHARSET_UTF), CHARSET_ISO88591);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertGBK2ISO(String input) {
        if (input == null) {
            return input;
        }
        try {
            return new String(input.getBytes(CHARSET_GBK), CHARSET_ISO88591);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * convert GBK to UTF-8
     *
     * @param input
     * @return
     * @throws UnsupportedEncodingException
     */
    public static byte[] convertGBK2UTF(byte[] input)
            throws UnsupportedEncodingException {
        return new String(input, CHARSET_GBK)
                .getBytes(CHARSET_UTF);
    }

    /***
     * convert from GBK to UTF-8
     *
     * @param input
     * @return
     */
    public static String convertGBK2UTF(String input) {
        if (input == null) {
            return input;
        }
        try {
            return new String(input.getBytes(CHARSET_GBK), CHARSET_UTF);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] convertFromeGBK(byte[] input, String toCharset)
            throws UnsupportedEncodingException {
        return new String(input, CHARSET_GBK).getBytes(toCharset);
    }

    /***
     * convert utf-8 to gbk
     *
     * @param input
     * @return
     */
    public static String convertUTF2GBK(String input) {
        if (input == null) {
            return input;
        }
        try {
            return new String(input.getBytes(CHARSET_UTF), CHARSET_GBK);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertISO2Specified(String oldName, String newEncoding) {
        if (oldName == null) {
            return oldName;
        }
        try {
            return new String(oldName.getBytes(CHARSET_ISO88591), newEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertISO2GBK(String input) {
        return convertISO2Specified(input, CHARSET_GBK);
    }

    public static String convertISO2UTF(String oldName) {
        return convertISO2Specified(oldName, CHARSET_UTF);
    }

    /**
     * 字符串过长,则使用"…"替换
     *
     * @param input
     * @param length 显示的字符的个数
     * @return
     */
    public static String omitTooLongString(String input, int length) {
        if (input == null || input.trim().equals(EMPTY)) {
            return input;
        }
        int len = input.length();
        if (len <= length) {
            return input;
        } else {
            input = input.substring(0, length);
            input += ENGLISH_ELLIPSIS;
        }
        return input;
    }

    /***
     * convert Map to query string
     *
     * @param orderInfo2PayMap
     * @return
     */
    public static String getQueryString(Map orderInfo2PayMap) {
        StringBuffer postData = new StringBuffer();
        if (StringUtil.isNullOrEmpty(orderInfo2PayMap)) {
            return EMPTY;
        }
        for (Object obj : orderInfo2PayMap.keySet()) {
            Object value = orderInfo2PayMap.get(obj);
            postData.append(obj).append("=").append(value).append("&");
        }

        return postData.toString().substring(0, postData.length() - 1);
    }

    public static Map parseQueryString(String notifyUrl) {
        String extra_common = notifyUrl.split("\\?")[1];
        String[] strs = extra_common.split("&");
        Map map = new HashMap();
        for (int i = 0; i < strs.length; i++) {
            String[] keyVal = strs[i].split("=");
            String val = null;
            if (keyVal.length > 1) {
                val = keyVal[1];
            } else {
                val = EMPTY;
            }
            map.put(keyVal[0], val);
        }
        return map;
    }

    public static String escapeHTML(String originStr) {
        String resultStr = originStr.substring(0);
        resultStr = resultStr.replace("'", "&apos;");//替换单引号
        resultStr = resultStr.replaceAll("&", "&amp;");//替换&
        resultStr = resultStr.replace("\"", "&quot;"); // 替换双引号
        resultStr = resultStr.replace("\t", "&nbsp;&nbsp;");// 替换跳格
        resultStr = resultStr.replace(" ", "&nbsp;");// 替换空格
        resultStr = resultStr.replace("<", "&lt;");//替换左尖括号
        resultStr = resultStr.replaceAll(">", "&gt;");//替换右尖括号
        return resultStr;
    }

    public static String title(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    public static int isContains2(String[] strArray, String j) {
        int index = NEGATIVE_ONE;
        if (isNullOrEmpty(strArray)) {
            return index;
        }
        int length2 = strArray.length;
        for (int ii = 0; ii < length2; ii++) {
            String i = strArray[ii];
            if (i.equals(j) || (equalsWildcard(j, i))) {
                index = ii;
                break;
            }
        }
        return index;
    }

    public static boolean equalsWildcard(String source, String regex) {
        regex = regex.replace(".", "\\.");
        regex = regex.replace("*", "(.*)");//加上小括号,方便查看
//		System.out.println(regex);
        Pattern p = Pattern.compile("^" + regex + "$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(source);
        return m.find();
    }
}
