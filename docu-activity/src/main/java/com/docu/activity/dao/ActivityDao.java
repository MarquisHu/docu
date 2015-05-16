package com.docu.activity.dao;

import java.util.List;

import com.docu.activity.dto.ActivityResult;
import com.docu.activity.model.Activity;
import com.docu.components.common.QueryBase;

public interface ActivityDao {
	Integer insertActivity(Activity activity);
	
	Integer updateActivity(Activity activity);
	
	Integer deleteActivity(String activityId);
	
	ActivityResult findActivity(String userId);
	
	int queryActivityTotal(QueryBase query);
	
	List<ActivityResult> queryActivity(QueryBase query);
	
	Long getSequenceUuid(String sequenceName);
}
