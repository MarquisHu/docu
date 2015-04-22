package com.docu.web.home.screen.acct;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.webx.WebxException;
import com.docu.account.dto.BillDetail;
import com.docu.account.dto.BillDetailCriteria;
import com.docu.account.service.BillService;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;
import com.docu.web.common.context.EnvUtils;

public class BillDetails {
	@Autowired
	private BillService billService;
	
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
		String activityId = rundata.getParameters().getString("activityId");
		String startDate = rundata.getParameters().getString("startDate");
		String endDate = rundata.getParameters().getString("endDate");
		
		BillDetailCriteria entity = new BillDetailCriteria();
		entity.setUserId(userId);
		entity.setActivityId(activityId);
		entity.setStartDate(startDate);
		entity.setEndDate(endDate);
		
		QueryBase query = new QueryBase(pageNum, entity);
		PageDO<BillDetail> page = billService.queryBillDetail(query);
		
		context.put("page", page);
		context.put("admin", admin);
		context.put("userId", userId);
		context.put("loginUserId", loginUserId);
	}
}
