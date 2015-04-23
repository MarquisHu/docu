package com.docu.activity.service;

import com.docu.activity.dto.Activity;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;


public interface ActivityService {
	int saveActivity(Activity user);
	
	int updateActivity(Activity user);
	
	Activity queryActivity(String userId);
	
	PageDO<Activity> queryActivity(QueryBase query);
}
