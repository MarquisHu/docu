package com.docu.web.common.context;

import com.docu.user.dto.DocumentUser;


public class LoginUserHelper {

	private final static ThreadLocal<DocumentUser> holder = new ThreadLocal<DocumentUser>();

	public static void saveUserInfo(DocumentUser user) {
		holder.set(user);
	}

	public static DocumentUser getLoginUserInfo() {
		return holder.get();
	}

	public static void clearUserContext() {
		holder.remove();
	}
}
