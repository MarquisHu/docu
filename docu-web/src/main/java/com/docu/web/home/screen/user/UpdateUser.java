package com.docu.web.home.screen.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.webx.WebxException;
import com.docu.user.dto.User;
import com.docu.user.service.UserService;
import com.docu.web.common.context.EnvUtils;

public class UpdateUser {
	
	@Autowired
	private UserService userService;
	
	public void execute(TurbineRunData rundata, Context context) throws WebxException {
		HttpSession session = rundata.getRequest().getSession();
		String admin = (String) session.getAttribute("admin");
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null) {
			rundata.setRedirectLocation(EnvUtils.getContextPath() + "/index.htm");
			return;
		}
		String userId = rundata.getParameters().getString("userId");
		if (userId == null) {
			userId = loginUserId;
		}
		User user = userService.queryUser(userId);
		context.put("user", user);
		context.put("admin", admin);
		context.put("userId", userId);
		context.put("loginUserId", loginUserId);
	}
}
