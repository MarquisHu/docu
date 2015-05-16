package com.docu.web.home.screen.activity;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.webx.WebxException;
import com.docu.activity.dto.ActivityCriteria;
import com.docu.activity.dto.ActivityResult;
import com.docu.activity.service.ActivityService;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;
import com.docu.web.common.context.EnvUtils;

public class Index {
	@Autowired
	private ActivityService activityService;
	
	public void execute(TurbineRunData rundata, Context context) throws WebxException {
		HttpSession session = rundata.getRequest().getSession();
		String admin = (String) session.getAttribute("admin");
		String loginUserId = (String) session.getAttribute("loginUserId");
		if (loginUserId == null || loginUserId.length() == 0) {
			rundata.setRedirectLocation(EnvUtils.getContextPath() + "/index.htm");
			return;
		}
		int pageNum = rundata.getParameters().getInt("pageNum");
		Long activityId = rundata.getParameters().getLong("activityId");
		String startDate = rundata.getParameters().getString("startDate");
		String endDate = rundata.getParameters().getString("endDate");
		
		ActivityCriteria entity = new ActivityCriteria();
		entity.setActivityId(activityId);
		entity.setStartDate(startDate);
		entity.setEndDate(endDate);
		
		QueryBase query = new QueryBase(pageNum, entity);
		
		PageDO<ActivityResult> page = activityService.queryActivity(query);
		
		context.put("page", page);
		context.put("admin", admin);
		if (activityId != null && activityId != 0) {
			context.put("activityId", activityId);
		}
		context.put("startDate", startDate);
		context.put("endDate", endDate);
		context.put("loginUserId", loginUserId);
	}
}
