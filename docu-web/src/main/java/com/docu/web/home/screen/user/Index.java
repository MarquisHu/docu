package com.docu.web.home.screen.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.webx.WebxException;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;
import com.docu.user.dto.User;
import com.docu.user.service.UserService;
import com.docu.web.common.context.EnvUtils;

public class Index {
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
		User entity = new User();
		int pageNum = rundata.getParameters().getInt("pageNum");
		String userId = rundata.getParameters().getString("userId");
		entity.setUserId(userId);
		
		QueryBase query = new QueryBase(pageNum, entity);
		PageDO<User> page = userService.queryUser(query);
		
		context.put("page", page);
		context.put("admin", admin);
		context.put("userId", userId);
		context.put("loginUserId", loginUserId);
	}
}
