package com.docu.activity.dao.impl;

import java.util.List;

import com.docu.activity.dao.ActivityDao;
import com.docu.activity.dto.Activity;
import com.docu.components.common.BaseDao;
import com.docu.components.common.QueryBase;

public class IbatisActivityDao extends BaseDao implements ActivityDao {
	private static final String NAMESPACE = "com.docu.activity.dao.ActivityDao.";
	
	@Override
	public Integer insertActivity(Activity activity) {
		return getSqlSessionTemplate().insert(NAMESPACE + "insertActivity", activity);
	}
	
	@Override
	public Integer updateActivity(Activity activity) {
		return getSqlSessionTemplate().update(NAMESPACE + "updateActivity", activity);
	}

	@Override
	public Activity findActivity(String userId) {
		return (Activity) getSqlSessionTemplate().selectOne(NAMESPACE + "findActivity", userId);
	}
	
	@Override
	public int queryActivityTotal(QueryBase query) {
		return (Integer) getSqlSessionTemplate().selectOne(NAMESPACE + "queryActivityTotal", query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> queryActivity(QueryBase query) {
		return (List<Activity>) getSqlSessionTemplate().selectList(NAMESPACE + "queryActivity", query);
	}
	
	@Override
	public Long getSequenceUuid(String sequenceName) {
		return (Long) getSqlSessionTemplate().selectOne(NAMESPACE + "getSequenceUuid", sequenceName);
	}
}
