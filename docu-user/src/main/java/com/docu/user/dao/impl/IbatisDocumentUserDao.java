package com.docu.user.dao.impl;

import java.util.List;

import com.docu.components.common.BaseDao;
import com.docu.components.common.QueryBase;
import com.docu.user.dao.DocumentUserDao;
import com.docu.user.dto.DocumentUser;

public class IbatisDocumentUserDao extends BaseDao implements DocumentUserDao {
	private static final String NAMESPACE = "com.docu.user.dao.DocumentUserDao.";
	
	@Override
	public Long insertUser(DocumentUser user) {
		return (long) getSqlSessionTemplate().insert(NAMESPACE + "insertUser", user);
	}

	@Override
	public DocumentUser findUser(String userId) {
		return (DocumentUser) getSqlSessionTemplate().selectOne(NAMESPACE + "findUser", userId);
	}

	@Override
	public int queryDocumentUserTotal(QueryBase query) {
		return (Integer) getSqlSessionTemplate().selectOne(NAMESPACE + "queryDocumentUserTotal", query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentUser> queryDocumentUser(QueryBase query) {
		return (List<DocumentUser>) getSqlSessionTemplate().selectList(NAMESPACE + "queryDocumentUser", query);
	}
}
