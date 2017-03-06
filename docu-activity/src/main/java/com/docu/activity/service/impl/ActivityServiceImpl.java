package com.docu.activity.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.docu.activity.dao.ActivityDao;
import com.docu.activity.dto.ActivityResult;
import com.docu.activity.model.Activity;
import com.docu.activity.service.ActivityService;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;

public class ActivityServiceImpl implements ActivityService {
	@Resource
	private ActivityDao activityDao;
	
	@Override
	public int saveActivity(Activity activity) {
		return activityDao.insertActivity(activity);
	}
	
	@Override
	public int updateActivity(Activity activity) {
		return activityDao.updateActivity(activity);
	}

	@Override
	public int deleteActivity(String activityId) {
		return activityDao.deleteActivity(activityId);
	}
	
	@Override
	public ActivityResult queryActivity(String activityId) {
		return activityDao.findActivity(activityId);
	}

	@Override
	public PageDO<ActivityResult> queryActivity(QueryBase query) {
		query.setTotal(activityDao.queryActivityTotal(query));
		List<ActivityResult> activities = activityDao.queryActivity(query);
		PageDO<ActivityResult> page = new PageDO<ActivityResult>(query.getPageNum(), query.getPageSize(), query.getTotal());
        page.setRows(activities);
        return page;
	}
	
	@Override
	public long getSequenceUuid(String sequenceName) {
		return activityDao.getSequenceUuid(sequenceName);
	}
}
