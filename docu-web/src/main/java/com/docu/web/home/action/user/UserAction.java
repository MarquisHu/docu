package com.docu.web.home.action.user;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.docu.account.dto.Account;
import com.docu.account.service.AccountService;
import com.docu.components.constants.app.Constants;
import com.docu.components.util.DateUtils;
import com.docu.user.dto.User;
import com.docu.user.service.UserService;
import com.docu.web.common.context.EnvUtils;

public class UserAction {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	public void doSaveUser(TurbineRunData rundata, Context context) {
		HttpSession session = rundata.getRequest().getSession();
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null) {
			rundata.setRedirectLocation(EnvUtils.getContextPath() + "/index.htm");
			return;
		}
		String userId = rundata.getParameters().getString("userId");
		String userName = rundata.getParameters().getString("userName");
		String telphoneNumber = rundata.getParameters().getString("telphoneNumber");
		String active = rundata.getParameters().getString("active");
		
		User user = userService.queryUser(userId);
		if (user != null) {
			String updateTime = DateUtils.formatDate(new Date());
			user.setUserId(userId);
			user.setUserName(userName);
			user.setTelphoneNumber(telphoneNumber);
			user.setActive(active);
			user.setUpdateBy(loginUserId);
			user.setUpdateTime(updateTime);
			
			int result = userService.updateUser(user);
			if (result == 1) {
				setDefaultAccount(loginUserId, userId, updateTime);
				rundata.setRedirectLocation(EnvUtils.getContextPath()+"/user/index.htm?userId=" + userId);
			} else {
				context.put("msg", "更新失败");
			}
		} else {
			context.put("msg", "更新失败");
		}
	}
	
	public void doAddUser(TurbineRunData rundata, Context context) {
		HttpSession session = rundata.getRequest().getSession();
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null) {
			rundata.setRedirectLocation(EnvUtils.getContextPath() + "/index.htm");
			return;
		}
		String userId = rundata.getParameters().getString("userId");
		String userName = rundata.getParameters().getString("userName");
		String telphoneNumber = rundata.getParameters().getString("telphoneNumber");
		String password = rundata.getParameters().getString("password");
		String active = rundata.getParameters().getString("active");
		String admin = rundata.getParameters().getString("admin");
		
		User user = userService.queryUser(userId);
		if (user == null) {
			user = new User();
			String updateTime = DateUtils.formatDate(new Date());
			password = DigestUtils.md5Hex(password);
			user.setUserId(userId);
			user.setUserName(userName);
			user.setTelphoneNumber(telphoneNumber);
			user.setUpdateBy(loginUserId);
			user.setUpdateTime(updateTime);
			setDefaultValue(user, password, active, admin);
			
			int result = userService.saveUser(user);
			if (result == 1) {
				setDefaultAccount(loginUserId, userId, updateTime);
				rundata.setRedirectLocation(EnvUtils.getContextPath()+"/user/index.htm?userId=" + userId);
			} else {
				context.put("msg", "新增用户失败");
			}
		} else {
			context.put("msg", "该用户账号已被人使用，请重新输入其他用户账号");
		}
	}
	
	public void doChangePassword(TurbineRunData rundata, Context context){
		HttpSession session = rundata.getRequest().getSession();
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null) {
			rundata.setRedirectLocation(EnvUtils.getContextPath() + "/index.htm");
			return;
		}
		String password = rundata.getParameters().getString("password");
		String password1 = rundata.getParameters().getString("password1");
		String password2 = rundata.getParameters().getString("password2");
		User user = userService.queryUser(loginUserId);
		if (user != null && user.getPassword().equals(DigestUtils.md5Hex(password))) {
			if (password1.equals(password2)) {
				user.setPassword(DigestUtils.md5Hex(password1));
				user.setUpdateTime(DateUtils.formatDate(new Date()));
				user.setUpdateBy(loginUserId);
				
				int result = userService.updateUser(user);
				if (result == 1) {
					context.put("msg", "修改成功");
				} else {
					context.put("msg", "修改失败");
				}
			} else {
				context.put("msg", "新密码与确认密码不一致，请重新输入");
			}
		} else {
			context.put("msg", "旧密码与当前密码不一致，请重新输入");
		}
	}
	
	private void setDefaultValue(User user, String password, String active, String admin) {
		if (password == null) {
			user.setPassword(DigestUtils.md5Hex(Constants.DEFAULT_PASSWORD));
		} else {
			user.setPassword(password);
		}
		
		if (active == null) {
			user.setActive(Constants.DEFAULT_ACTIVE_STATUS);
		} else {
			user.setActive(active);
		}
		
		if (admin == null) {
			user.setAdmin(Constants.DEFAULT_ADMIN_STATUS);
		} else {
			user.setAdmin(admin);
		}
	}
	
	private void setDefaultAccount(String loginUserId, String userId, String updateTime) {
		Account account = accountService.queryAccount(userId);
		if (account == null) {
			String sequenceName = Constants.ACCOUNT_UUID_SEQUENCE_NAME;
			long accountId = accountService.getSequenceUuid(sequenceName);
			
			account = new Account();
			account.setAccountId(accountId);
			account.setUserId(userId);
			account.setBalance(0.0f);
			account.setUpdateBy(loginUserId);
			account.setUpdateTime(updateTime);
			accountService.saveAccount(account);
		}
	}
}
