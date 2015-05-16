package com.docu.web.common.context;

import com.docu.user.model.User;


public class LoginUserHelper {

	private final static ThreadLocal<User> holder = new ThreadLocal<User>();

	public static void saveUserInfo(User user) {
		holder.set(user);
	}

	public static User getLoginUserInfo() {
		return holder.get();
	}

	public static void clearUserContext() {
		holder.remove();
	}
}
