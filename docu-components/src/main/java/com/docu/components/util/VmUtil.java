package com.docu.components.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public final class VmUtil {

	public static String handleEmpty(String value, String standby) {
		if (StringUtils.isEmpty(value)) {
			return standby;
		}
		return value;
	}


	public static String omitDecimal(Number d, String standby) {
		String result = formatRoundNumber(d, 0, BigDecimal.ROUND_HALF_UP);
		if (StringUtils.isEmpty(result)) {
			return standby;
		} else if (result.length() > 2) {
			String lastTwo = result.substring(result.length() - 2);
			if (".0".equals(lastTwo)) {
				return result.substring(0, result.length() - 2);
			}
		}
		return result;
	}


	public static String formatNumber(Number num) {
		return formatNumber(num, "###,###,###,###.###");
	}
	
	public static String longList2String(List<Long> list){
        StringBuilder sb = new StringBuilder();
        if (list != null) {
            for (Long item : list) {
                sb.append(item);
                sb.append(":");
            }
        }
        return sb.toString();
	}

	public static String formatNumber(Number num, String pattern) {
		if (num == null) {
			return "";
		}
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(num);
	}


	public static String formatDate(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}


	public static String showMoney(Number param) {
		if (param == null)
			return "";
		long money = param.longValue();
		StringBuffer sb = new StringBuffer();
		sb.append((money >= 0) ? "" : "-");
		sb.append(Math.abs(money) / 100);
		sb.append(".");
		sb.append((Math.abs(money) / 10) % 10);
		sb.append(Math.abs(money) % 10);
		return sb.toString();
	}

	public static String showMoney(String money) {
		if (money == null) {
			return "";
		}
		return showMoney(NumberUtils.toLong(money));
	}


	public static String formatRoundNumber(Number d, String defaultStr) {
		String result = formatRoundNumber(d, 1, BigDecimal.ROUND_HALF_UP);
		return StringUtils.isEmpty(result) ? defaultStr : result;
	}


	public static String formatRoundNumber(Number d) {
		return formatRoundNumber(d, 1, BigDecimal.ROUND_HALF_UP);
	}


	public static String formatNumber(Number d, int dot) {
		return formatRoundNumber(d, dot, BigDecimal.ROUND_HALF_UP);
	}

	public static String formatRoundNumber(Number d, int dot, int roundMode) {
		if (d == null) {
			return "";
		}
		BigDecimal bd = null;
		if (d instanceof Double) {
			bd = new BigDecimal(d.doubleValue());
		} else if (d instanceof Float) {
			bd = new BigDecimal(d.floatValue());
		} else {
			return String.valueOf(d.longValue());
		}

		return String.valueOf(bd.setScale(dot, roundMode).doubleValue());
	}

	private static String timestamp = new SimpleDateFormat("yyMMddHH").format(new Date());


	public static String timestamp() {
		return timestamp;
	}


	public static void freshTimestamp() {
		timestamp = new SimpleDateFormat("yyMMddHHmm").format(new Date());
	}
	
	public static boolean menuEq(String menu,String match){
		if(StringUtils.isBlank(menu))return false;
	    String[] arry = menu.split("#");
	    for(String m : arry){
	       if(match.equals(m)){
	          return true;
	       }
	    }
	    return false;
	}
	
	public static String formatMoneyNumber(Number num){
        String money = "0.00";
        
        if(num == null || num.equals(0) || num.equals(0.0) || num.equals("")){
            return money;
        }
        
        NumberFormat nf = NumberFormat.getCurrencyInstance();  
        money = nf.format(num).toString();
        money = money.substring(1,money.length());
        money = money.replace( ",", "");
        return money;
    }
	
	public static String getImgUrl(String picName,int width,int height){
		if(StringUtils.isBlank(picName)){
			return "";
		}
		return ImgUtil.getImgUrl(width, height, picName);
	}

	public static void main(String[] args) {
		System.out.println(formatNumber(1.25D));
		System.out.println(showMoney("-143523453425"));
		System.out.println(formatNumber(-143523453425D));
		long numLong = 0;
		System.out.println(formatRoundNumber(numLong, 0, BigDecimal.ROUND_HALF_UP));
		System.out.println(omitDecimal(numLong, "-null-"));
		Double numDouble = Double.MAX_VALUE;
		System.out.println(formatRoundNumber(numDouble, 0, BigDecimal.ROUND_HALF_UP));
		System.out.println(omitDecimal(numDouble, "-null-"));
		System.out.println(formatRoundNumber(0.00123, 0, BigDecimal.ROUND_HALF_UP));
		System.out.println("==" + omitDecimal(Double.valueOf(0), "-null-"));

		System.out.println(formatRoundNumber(123.54, "-null-"));
	}
}
