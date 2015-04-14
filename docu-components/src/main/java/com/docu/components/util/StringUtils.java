package com.docu.components.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public final class StringUtils extends org.apache.commons.lang.StringUtils {
    
    public static final String ALL_NUMBER_VALUE = "0123456789";
    public static final String ALL_LOWERCASE_VALUE = "abcdefghijklmnopqrstuvwxyz";
    public static final String ALL_UPPERCASE_VALUE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
	public static String toDBC(String input) {
		if (isEmpty(input)) {
			return "";
		}
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);
			}
		}
		String returnString = new String(c);
		return returnString;
	}

	public static boolean isEmptyWithTrim(String str) {
		return isEmpty(trim(str));
	}

	public static List<String> splitToStrList(String str) {
		return split(str, ',', new TypeConvertor<String>() {
			@Override
			public String convert(String str) {
				return str;
			}
		});
	}

	public static List<Long> splitToLongList(String str) {
		return split(str, ',', new TypeConvertor<Long>() {
			@Override
			public Long convert(String str) {
				if (isEmpty(str)) {
					return null;
				}
				return Long.parseLong(str);
			}
		});
	}

	public static <T> List<T> split(String str, char separatorChar,
			TypeConvertor<T> typeConvertor) {
		if (isEmptyWithTrim(str)) {
			return null;
		}

		String[] strs = split(str, separatorChar);
		if (strs == null || strs.length == 0) {
			return null;
		}
		List<T> list = new ArrayList<T>();
		for (String tmp : strs) {
			list.add(typeConvertor.convert(trim(tmp)));
		}
		return list;
	}

	public interface TypeConvertor<T> {
		public T convert(String str);
	}

	public static String trim(String str, String stripChars) {
		return trim(str, stripChars, 0);
	}

	private static String trim(String str, String stripChars, int mode) {
		if (str == null) {
			return null;
		}

		int length = str.length();
		int start = 0;
		int end = length;

		if (mode <= 0) {
			if (stripChars == null) {
				while ((start < end)
						&& (Character.isWhitespace(str.charAt(start)))) {
					start++;
				}
			} else if (stripChars.length() == 0) {
				return str;
			} else {
				while ((start < end)
						&& (stripChars.indexOf(str.charAt(start)) != -1)) {
					start++;
				}
			}
		}

		if (mode >= 0) {
			if (stripChars == null) {
				while ((start < end)
						&& (Character.isWhitespace(str.charAt(end - 1)))) {
					end--;
				}
			} else if (stripChars.length() == 0) {
				return str;
			} else {
				while ((start < end)
						&& (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
					end--;
				}
			}
		}

		if ((start > 0) || (end < length)) {
			return str.substring(start, end);
		}

		return str;
	}

	public static String trimToEmpty(String str, String stripChars) {
		String result = trim(str, stripChars);

		if (result == null) {
			return "";
		}

		return result;
	}

	public static Boolean hasSpecialCharacter(String input) {
		String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		return p.matcher(input).find();
	}

	public static String getNotNullString(Object o) {
		if (o == null) {
			return "";
		}
		return String.valueOf(o);
	}

	public static String getDefaultString(Object o, String def) {
		if (o == null) {
			return def;
		}
		return String.valueOf(o);
	}

	public static boolean isEmpty(String... strings) {
		for (String str : strings) {
			if (StringUtils.isEmpty(str)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
    public static String createNumberRandom(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length cannot be less than 1");
        }
        int vLen = ALL_NUMBER_VALUE.length();
        String result = "";
        for (int i = 1; i <= length; i++) {
            double dr = Math.random() * vLen;
            int ir = (int) Math.floor(dr);
            result += ALL_NUMBER_VALUE.charAt(ir);
        }
        return result;
    }

    public static String createUppercaseRandom(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length cannot be less than 1");
        }
        int vLen = ALL_UPPERCASE_VALUE.length();
        String result = "";
        for (int i = 0; i < length; i++) {
            double dr = Math.random() * vLen;
            int ir = (int) Math.floor(dr);
            result += ALL_UPPERCASE_VALUE.charAt(ir);
        }
        return result;
    }

    public static String createLowercaseRandom(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length cannot be less than 1");
        }
        int vLen = ALL_LOWERCASE_VALUE.length();
        String result = "";
        for (int i = 0; i < length; i++) {
            double dr = Math.random() * vLen;
            int ir = (int) Math.floor(dr);
            result += ALL_LOWERCASE_VALUE.charAt(ir);
        }
        return result;
    }

    public static String createMixRandom(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length cannot be less than 1");
        }
        String values = ALL_NUMBER_VALUE + ALL_UPPERCASE_VALUE + ALL_LOWERCASE_VALUE;
        int vLen = values.length();
        String result = "";
        for (int i = 0; i < length; i++) {
            double dr = Math.random() * vLen;
            int ir = (int) Math.floor(dr);
            result += values.charAt(ir);
        }
        return result;
    }
    
    public static String concat(String ...subStrings){
    	StringBuilder builder = new StringBuilder();
    	for (String subString : subStrings) {
    		builder.append(subString);
		}
    	return builder.toString();
    }

	public static void main(String[] args) {
		String nick = "abc!def";
		System.out.println(toDBC(nick));
		System.out.println(hasSpecialCharacter(nick));
	}
}
