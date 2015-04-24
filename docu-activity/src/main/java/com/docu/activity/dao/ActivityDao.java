package com.docu.activity.dao;

import java.util.List;

import com.docu.activity.dto.Activity;
import com.docu.components.common.QueryBase;

public interface ActivityDao {
	Integer insertActivity(Activity activity);
	
	Integer updateActivity(Activity activity);
	
	Activity findActivity(String userId);
	
	int queryActivityTotal(QueryBase query);
	
	List<Activity> queryActivity(QueryBase query);
	
	Long getSequenceUuid(String sequenceName);
}
