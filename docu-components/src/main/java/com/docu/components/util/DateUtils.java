package com.docu.components.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

import com.docu.components.exception.UtilException;

public final class DateUtils extends org.apache.commons.lang.time.DateUtils {
    
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS="yyyy-MM-dd HH:mm:ss";

    public static String formatDate(Date date){
        String d=formatDate(date, FORMAT_YYYY_MM_DD_HH_MM_SS);
        return d;
    }
    
    public static String formatDate(Date date,String pattern){
        String d=null;
        if(date!=null)
            d=DateFormatUtils.format(date, pattern);
        return d;
    }

    public static String formatDate(Integer dataVersion, String pattern) throws UtilException {
        String tmp = dataVersion.toString();
        DateFormat sf = new SimpleDateFormat("yyyyMMdd");
        Date date;
        try {
            date = sf.parse(tmp);
        } catch (ParseException e) {
            throw new UtilException("字符串解析成日期对象时异常", e);
        }
        DateFormat sf2 = new SimpleDateFormat(pattern);
        return sf2.format(date);
    }
    
    public static int addDays(Integer dataVersion, int days) throws UtilException {
        String tmp = dataVersion.toString();
        DateFormat sf = new SimpleDateFormat("yyyyMMdd");
        Date date;
        try {
            date = sf.parse(tmp);
        } catch (ParseException e) {
            throw new UtilException("String is parsed into the date object is abnormal", e);
        }

        String dateStr = sf.format(addDays(date, days));
        return Integer.parseInt(dateStr);
    }

    public static int getYesterdayDataVersion() {
        Date d = addDays(new Date(), -1);
        DateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(sf.format(d));
    }

    public static Date formatStringToDate(String dateStr,String formatStr){
        if(StringUtils.isBlank(dateStr)){
            return null;
        }
        DateFormat sdf=new SimpleDateFormat(formatStr);
        Date date=null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
