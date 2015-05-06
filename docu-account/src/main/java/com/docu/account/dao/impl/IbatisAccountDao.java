package com.docu.account.dao.impl;

import java.util.List;

import com.docu.account.dao.AccountDao;
import com.docu.account.dto.Account;
import com.docu.account.dto.AccountBalance;
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
	public String getTotalBalance() {
		return (String) getSqlSessionTemplate().selectOne(NAMESPACE + "getTotalBalance");
	}

	@Override
	public AccountBalance findAccountBalance(String userId) {
		return (AccountBalance) getSqlSessionTemplate().selectOne(NAMESPACE + "findAccountBalance", userId);
	}
	
	@Override
	public int queryAccountBalanceTotal(QueryBase query) {
		return (Integer) getSqlSessionTemplate().selectOne(NAMESPACE + "queryAccountBalanceTotal", query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountBalance> queryAccountBalance(QueryBase query) {
		return (List<AccountBalance>) getSqlSessionTemplate().selectList(NAMESPACE + "queryAccountBalance", query);
	}
	
	@Override
	public Long getSequenceUuid(String sequenceName) {
		return (Long) getSqlSessionTemplate().selectOne(NAMESPACE + "getSequenceUuid", sequenceName);
	}
}
