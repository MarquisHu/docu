package com.docu.web.home.screen.activity;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.webx.WebxException;
import com.docu.user.dto.UserResult;
import com.docu.user.service.UserService;
import com.docu.web.common.context.EnvUtils;

public class AddActivity {
	@Autowired
	private UserService userService;
	
	public void execute(TurbineRunData rundata, Context context) throws WebxException {
		HttpSession session = rundata.getRequest().getSession();
		String admin = (String) session.getAttribute("admin");
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null || loginUserId.length() == 0) {
			rundata.setRedirectLocation(EnvUtils.getContextPath() + "/index.htm");
			return;
		}
		
		Float expenseAmount = rundata.getParameters().getFloat("expenseAmount");
		String remark = rundata.getParameters().getString("remark");
		String location = rundata.getParameters().getString("location");
		String activityTime = rundata.getParameters().getString("activityTime");
		Integer percent = rundata.getParameters().getInt("percent");
		
		List<UserResult> users = userService.findUsers();
		
		context.put("users", users);
		if (expenseAmount != null && expenseAmount != 0) {
			context.put("expenseAmount", expenseAmount);
		}
		context.put("remark", remark);
		context.put("location", location);
		context.put("activityTime", activityTime);
		context.put("percent", percent);
		context.put("admin", admin);
		context.put("loginUserId", loginUserId);
	}
}
