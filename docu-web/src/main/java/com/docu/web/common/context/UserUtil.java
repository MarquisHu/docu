package com.docu.web.common.context;

import com.docu.user.dto.User;

public final class UserUtil {

	public static boolean isLogin() {
		return LoginUserHelper.getLoginUserInfo() != null;
	}

	public static User getLoginUser() {
		return LoginUserHelper.getLoginUserInfo();
	}

	
	public static User getLoginUserInfo(){
		return LoginUserHelper.getLoginUserInfo();
	}
}
