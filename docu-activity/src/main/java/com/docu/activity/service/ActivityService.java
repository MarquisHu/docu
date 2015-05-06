package com.docu.activity.service;

import com.docu.activity.dto.Activity;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;


public interface ActivityService {
	int saveActivity(Activity activity);
	
	int updateActivity(Activity activity);
	
	int deleteActivity(String activityId);
	
	Activity queryActivity(String activityId);
	
	PageDO<Activity> queryActivity(QueryBase query);
	
	long getSequenceUuid(String sequenceName);
}
