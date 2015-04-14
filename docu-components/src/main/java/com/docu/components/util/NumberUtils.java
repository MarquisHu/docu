package com.docu.components.util;

import java.math.BigDecimal;

public final class NumberUtils extends org.apache.commons.lang.math.NumberUtils {

    public static Double fenToYuan(Long money, double defaultValue) {
        if (money == null)
            return defaultValue;
        StringBuffer sb = new StringBuffer();
        sb.append((money >= 0) ? "" : "-");
        sb.append(Math.abs(money) / 100);
        sb.append(".");
        sb.append((Math.abs(money) / 10) % 10);
        sb.append(Math.abs(money) % 10);
        return new Double(sb.toString());
    }

    public static Long yuanToFen(String f) {
        if (f == null || "".equals(f)) {
            return 0l;
        }
        return yuanToFen(toFloat(f));
    }

    public static Long yuanToFen(Float f) {
        if (f == null) {
            return 0l;
        }
        BigDecimal bd = new BigDecimal(f * 100);
        return bd.setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
    }

    public static void main(String[] args) {
        String i = "324.236";
        System.out.println(yuanToFen(toFloat(i)));
    }

    public static boolean isNullOrZero(Number num) {
        if (num == null || num.longValue() == 0) {
            return true;
        }
        return false;
    }

    public static double roundNumber(Number d, double defaultStr) {
        Double result = roundNumber(d, 1, BigDecimal.ROUND_HALF_UP);
        return result == null ? defaultStr : result;
    }

    public static Double roundNumber(Number d) {
        return roundNumber(d, 1, BigDecimal.ROUND_HALF_UP);
    }

    public static Double roundNumber(Number d, int dot, int roundMode) {
        if (d == null) {
            return null;
        }
        BigDecimal bd = null;
        if (d instanceof Double) {
            bd = new BigDecimal(d.doubleValue());
        } else if (d instanceof Float) {
            bd = new BigDecimal(d.floatValue());
        } else {
            bd = new BigDecimal(d.longValue());
        }
        return bd.setScale(dot, roundMode).doubleValue();
    }
    
    public static boolean isLongValueEqual(Long number1,Long number2){
        if(number1==null&&number2==null){
            return true;
        }
        if(number1==null||number2==null){
            return false;
        }
        return (long)number1 == (long)number2;
    }
}
