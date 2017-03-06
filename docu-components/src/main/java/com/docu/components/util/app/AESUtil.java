package com.docu.components.util.app;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.docu.components.constants.app.Constants;


public class AESUtil {
	public static byte[] encryptionStr (String str) {
		try{
			return encryptionStr(str,PropertiesUtil.get(Constants.PAWN_KEY));
		}catch (Exception e) {
			return null;
		}
	}
	public static String decryptStr (byte[] str) {
		try{
			return decryptStr(str,PropertiesUtil.get(Constants.PAWN_KEY));
		}catch (Exception e) {
			return null;
		}
	}

	public static byte[] encryptionStr(String str,String pawnKey) throws Exception{
		try{
			byte[] raw = pawnKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(pawnKey.getBytes());   
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			
			byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
			System.out.println(encrypted);
			return encrypted;
		}catch(Exception e){
			return null;
		}
	}

	public static String decryptStr(byte[] str,String pawnKey){
		try {   
			if (pawnKey == null) {   
				System.out.print("Key为空null");
				return null;   
			}   
			if (pawnKey.length() != 16) {   
				System.out.print("Key长度不是16位");   
				return null;   
			}   
			byte[] raw = pawnKey.getBytes("ASCII");   
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");   
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");   
			IvParameterSpec iv = new IvParameterSpec(pawnKey  
					.getBytes());   
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			try {   
				String originalString = new String(cipher.doFinal(str),"UTF-8");
				return originalString;
			} catch (Exception e) {   
				System.out.println(e.toString());   
				return null;   
			}   
		} catch (Exception ex) {   
			System.out.println(ex.toString());
			return null;   
		}

	}
}
