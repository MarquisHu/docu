package com.docu.account.dao.impl;

import java.util.List;

import com.docu.account.dao.AccountDao;
import com.docu.account.dto.Account;
import com.docu.components.common.BaseDao;
import com.docu.components.common.QueryBase;

public class IbatisAccountDao extends BaseDao implements AccountDao {
	private static final String NAMESPACE = "com.docu.account.dao.AccountDao.";
	
	@Override
	public Integer insertAccount(Account account) {
		return getSqlSessionTemplate().insert(NAMESPACE + "insertAccount", account);
	}
	
	@Override
	public Integer updateAccount(Account account) {
		return getSqlSessionTemplate().update(NAMESPACE + "updateAccount", account);
	}

	@Override
	public Account findAccount(String userId) {
		return (Account) getSqlSessionTemplate().selectOne(NAMESPACE + "findAccount", userId);
	}
	
	@Override
	public int queryAccountTotal(QueryBase query) {
		return (Integer) getSqlSessionTemplate().selectOne(NAMESPACE + "queryAccountTotal", query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> queryAccount(QueryBase query) {
		return (List<Account>) getSqlSessionTemplate().selectList(NAMESPACE + "queryAccount", query);
	}
}
