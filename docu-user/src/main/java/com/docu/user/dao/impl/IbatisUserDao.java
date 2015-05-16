package com.docu.user.dao.impl;

import java.util.List;

import com.docu.components.common.BaseDao;
import com.docu.components.common.QueryBase;
import com.docu.user.dao.UserDao;
import com.docu.user.dto.UserResult;
import com.docu.user.model.User;

public class IbatisUserDao extends BaseDao implements UserDao {
	private static final String NAMESPACE = "com.docu.user.dao.UserDao.";
	
	@Override
	public Integer insertUser(User user) {
		return getSqlSessionTemplate().insert(NAMESPACE + "insertUser", user);
	}
	
	@Override
	public Integer updateUser(User user) {
		return getSqlSessionTemplate().update(NAMESPACE + "updateUser", user);
	}

	@Override
	public User findUser(String userId) {
		return (User) getSqlSessionTemplate().selectOne(NAMESPACE + "findUser", userId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserResult> findUsers() {
		return (List<UserResult>) getSqlSessionTemplate().selectList(NAMESPACE + "findUsers");
	}

	@Override
	public int queryUserTotal(QueryBase query) {
		return (Integer) getSqlSessionTemplate().selectOne(NAMESPACE + "queryUserTotal", query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryUser(QueryBase query) {
		return (List<User>) getSqlSessionTemplate().selectList(NAMESPACE + "queryUser", query);
	}
}
