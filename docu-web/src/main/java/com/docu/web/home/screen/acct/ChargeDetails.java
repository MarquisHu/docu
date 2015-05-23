package com.docu.web.home.screen.acct;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.webx.WebxException;
import com.docu.account.dto.AccountDetailCriteria;
import com.docu.account.dto.ChargeDetailResult;
import com.docu.account.service.AccountDetailService;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;
import com.docu.components.constants.app.Constants;
import com.docu.web.common.context.EnvUtils;

public class ChargeDetails {
	@Autowired
	private AccountDetailService detailService;
	
	public void execute(TurbineRunData rundata, Context context) throws WebxException {
		HttpSession session = rundata.getRequest().getSession();
		String admin = (String) session.getAttribute("admin");
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null || loginUserId.length() == 0) {
			rundata.setRedirectLocation(EnvUtils.getContextPath() + "/index.htm");
			return;
		}
		int pageNum = rundata.getParameters().getInt("pageNum");
		String userId = rundata.getParameters().getString("userId");
		String startDate = rundata.getParameters().getString("startDate");
		String endDate = rundata.getParameters().getString("endDate");
		
		AccountDetailCriteria entity = new AccountDetailCriteria();
		if (Constants.COMMON_USER_ID.equalsIgnoreCase(loginUserId)) {
			entity.setUserId(loginUserId);
		} else if (Constants.USER_TYPE_ADMIN_NO.equals(admin)) {
			entity.setUserId(loginUserId);
		} else {
			entity.setUserId(userId);
		}
		entity.setStartDate(startDate);
		entity.setEndDate(endDate);
		
		QueryBase query = new QueryBase(pageNum, entity);
		PageDO<ChargeDetailResult> page = detailService.queryChargeDetails(query);
		
		context.put("page", page);
		context.put("admin", admin);
		context.put("userId", userId);
		context.put("startDate", startDate);
		context.put("endDate", endDate);
		context.put("loginUserId", loginUserId);
	}
}
