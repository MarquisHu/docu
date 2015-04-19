package com.docu.account.dao.impl;

import java.util.List;

import com.docu.account.dao.AccountBalanceDao;
import com.docu.account.dto.AccountBalance;
import com.docu.components.common.BaseDao;
import com.docu.components.common.QueryBase;

public class IbatisAccountBalanceDao extends BaseDao implements AccountBalanceDao {
	private static final String NAMESPACE = "com.docu.account.dao.AccountBalanceDao.";

	@Override
	public int queryAccountBalanceTotal(QueryBase query) {
		return (Integer) getSqlSessionTemplate().selectOne(NAMESPACE + "queryAccountBalanceTotal", query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountBalance> queryAccountBalance(QueryBase query) {
		return (List<AccountBalance>) getSqlSessionTemplate().selectList(NAMESPACE + "queryAccountBalance", query);
	}
}
