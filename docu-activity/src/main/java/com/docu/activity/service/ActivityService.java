package com.docu.activity.service;

import com.docu.activity.dto.ActivityResult;
import com.docu.activity.model.Activity;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;


public interface ActivityService {
	int saveActivity(Activity activity);
	
	int updateActivity(Activity activity);
	
	int deleteActivity(String activityId);
	
	ActivityResult queryActivity(String activityId);
	
	PageDO<ActivityResult> queryActivity(QueryBase query);
	
	long getSequenceUuid(String sequenceName);
}
