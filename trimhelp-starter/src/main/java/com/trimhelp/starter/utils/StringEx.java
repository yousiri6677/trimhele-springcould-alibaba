package com.trimhelp.starter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author hrimhelp
 * @Data 2019/2/24
 * @Email xieheng91@163.com
 * @Desc  string工具类
 */
public class StringEx
{
    private static final Logger logger = LoggerFactory.getLogger(StringEx.class);

    public static final String newRid(String serial)
    {
        return newUUID() + serial;
    }

    public static final String newUUID()
    {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    public static final String safetyChar(String c)
    {
        try
        {
            c = new String(c.getBytes("iso-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("safetyChar", e);
            return "";
        }

        return c.replace("\"", "").replace("'", "");
    }

    public static final boolean stringIsNullOrEmpty(String s)
    {
        return (s == null) || (s.isEmpty());
    }

    public static final <T> String stringJoin(List<T> array, String separator)
    {
        if (array == null) {
            return null;
        }
        int arraySize = array.size();

        int bufSize = arraySize == 0 ? 0 :
                ((array.get(0) == null ? 16 : array
                        .get(0)
                        .toString().length()) + 1) * arraySize;

        StringBuffer buf = new StringBuffer(bufSize);

        for (int i = 0; i < arraySize; i++) {
            if (i > 0) {
                buf.append(separator);
            }
            if (array.get(i) != null) {
                buf.append(array.get(i));
            }
        }
        return buf.toString();
    }

    public static final String stringJoin(String[] array, String separator)
    {
        if (array == null) {
            return null;
        }
        int arraySize = array.length;
        int bufSize = arraySize == 0 ? 0 : ((array[0] == null ? 16 : array[0].toString().length()) + 1) * arraySize;
        StringBuffer buf = new StringBuffer(bufSize);

        for (int i = 0; i < arraySize; i++) {
            if (i > 0) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    public static List<String> stringToList(String s)
    {
        if ((s == null) || (s.isEmpty())) {
            return new LinkedList();
        }
        List list = new ArrayList();
        String[] str = s.split(";");
        for (String string : str) {
            list.add(string);
        }
        return list;
    }

    public static String removeStartChar(String str, String c)
    {
        if ((str.length() > 1) && (str.startsWith(c))) {
            return str.substring(1);
        }
        return str;
    }

    public static String removeEndChar(String str, String c)
    {
        if ((str.length() > 1) || (str.endsWith(c))) {
            return str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
     * 获取订单号
     * @return
     */
    public static String getOrderIdByTime() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
        return newDate+result;
    }

    /**
     * 生成18位id
     * @return String
    */
    public static String getIdByTime() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmssSS");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }
        String realNo = newDate+result;
        if (realNo.length() < 18){
            realNo += random.nextInt(10);
        }
        return realNo;
    }

    public static boolean equalsObject(Object s,Object t){
        if(null != s && null != t){
            return s.toString().equals(t.toString());
        }
        return false;
    }

    public static String UUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static Integer getRandomForInteger(Integer minValue,Integer maxValue){
        Random random=new Random();
        return random.nextInt(maxValue-minValue)+minValue;
    }
    public static Integer stringToInteger(String value) {
        Integer l;
        value = nullToString(value);
        if ("".equals(value)) {
            l = 0;
        } else {
            try {
                l = Integer.valueOf(value);

            } catch (Exception e) {
                l = 0;
            }
        }
        return l;
    }

    public static Byte stringToByte(String value) {
        Byte l;
        value = nullToString(value);
        if ("".equals(value)) {
            l = 0;
        } else {
            try {
                l = Byte.valueOf(value);

            } catch (Exception e) {
                l = 0;
            }
        }
        return l;
    }

    public static String integerToString(Integer value) {
        if (value == null) {
            return "0";
        }

        return value.toString();
    }
    public static Long stringToLong(String value) {
        Long l;
        value = nullToString(value);
        if ("".equals(value)) {
            l = 0L;
        } else {
            try {
                l = Long.valueOf(value);
            } catch (Exception e) {
                l = 0L;
            }
        }
        return l;
    }
    public static BigDecimal nullToBigDecimal(BigDecimal value) {
        return value == null || "".equals(value.toString()) ? new BigDecimal(0) : value;
    }

    public static Long nullToLong(Object value) {
        return value == null || "null".equals(value.toString()) ? 0L : stringToLong(value.toString());
    }

    public static Integer nullToInteger(Object value) {
        return value == null || "null".equals(value.toString()) ? 0 : stringToInteger(value.toString());
    }

    public static Byte nullToByte(Object value) {
        return value == null || "null".equals(value.toString()) ? Byte.valueOf("0") : stringToByte(value.toString());
    }

    public static List<String> nullArrayToList(String[] strArrays) {
        List<String> arrayList = new ArrayList<String>();
        if (strArrays != null && strArrays.length > 0) {
            for (int i = 0; i < strArrays.length; i++) {
                arrayList.add(StringEx.null2Str(strArrays[i]));
            }
        }
        return arrayList;
    }
    // 将NULL转换成空字符串
    public static String null2Str(Object value) {
        return value == null || "null".equals(value.toString()) ? "" : value.toString().trim();
    }

    public static String null2Str(String value) {
        return value == null || "null".equals(value) ? "" : value.trim();
    }

    public static String nullToString(String value) {
        return value == null || "null".equals(value) ? "" : value.trim();
    }

    public static String nullToString(Object value) {
        return value == null ? "" : value.toString().trim();
    }

    /**
     * 将map转成string
     * @param map
     * @return
     */
    public static String getMapToString(Map<String,Object> map){
        Set<String> keySet = map.keySet();
        //将set集合转换为数组
        String[] keyArray = keySet.toArray(new String[keySet.size()]);

        //因为String拼接效率会很低的，所以转用StringBuilder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyArray.length; i++) {
            // 参数值为空，则不参与签名 这个方法trim()是去空格
            if ((String.valueOf(map.get(keyArray[i]))).trim().length() > 0) {
                sb.append(keyArray[i]).append(":").append(String.valueOf(map.get(keyArray[i])).trim());
                if(i != keyArray.length-1){
                    sb.append(",");
                }
            }
        }
        return sb.toString();
    }
}