package com.docu.user.dao.impl;

import java.util.List;

import com.docu.components.common.BaseDao;
import com.docu.components.common.QueryBase;
import com.docu.user.dao.UserDao;
import com.docu.user.dto.User;

public class IbatisUserDao extends BaseDao implements UserDao {
	private static final String NAMESPACE = "com.docu.user.dao.UserDao.";
	
	@Override
	public Long insertUser(User user) {
		return (long) getSqlSessionTemplate().insert(NAMESPACE + "insertUser", user);
	}

	@Override
	public User findUser(String userId) {
		return (User) getSqlSessionTemplate().selectOne(NAMESPACE + "findUser", userId);
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
