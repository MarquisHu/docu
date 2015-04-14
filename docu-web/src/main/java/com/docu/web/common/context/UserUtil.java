package com.docu.web.common.context;

import com.docu.user.dto.DocumentUser;

public final class UserUtil {

	public static boolean isLogin() {
		return LoginUserHelper.getLoginUserInfo() != null;
	}

	public static DocumentUser getLoginUser() {
		return LoginUserHelper.getLoginUserInfo();
	}

	
	public static DocumentUser getLoginUserInfo(){
		return LoginUserHelper.getLoginUserInfo();
	}
}
