package com.docu.account.dao.impl;

import java.util.List;

import com.docu.account.dao.AccountDetailDao;
import com.docu.account.dto.AccountBalanceCriteria;
import com.docu.account.dto.AccountDetailCriteria;
import com.docu.account.dto.AccountDetailResult;
import com.docu.account.model.AccountDetail;
import com.docu.components.common.BaseDao;
import com.docu.components.common.QueryBase;

public class IbatisAccountDetailDao extends BaseDao implements AccountDetailDao {
	private static final String NAMESPACE = "com.docu.account.dao.AccountDetailDao.";
	
	@Override
	public Integer insertAccountDetail(AccountDetail detail) {
		return getSqlSessionTemplate().insert(NAMESPACE + "insertAccountDetail", detail);
	}

	@Override
	public Integer deleteAccountDetail(String activityId) {
		return getSqlSessionTemplate().delete(NAMESPACE + "deleteAccountDetail", activityId);
	}
	
	@Override
	public String getTotalBalance(AccountBalanceCriteria criteria) {
		return (String) getSqlSessionTemplate().selectOne(NAMESPACE + "getTotalBalance", criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountDetail> findAccountDetails(AccountDetailCriteria criteria) {
		return (List<AccountDetail>) getSqlSessionTemplate().selectList(NAMESPACE + "findAccountDetails", criteria);
	}

	@Override
	public int queryAccountDetailTotal(QueryBase query) {
		return (Integer) getSqlSessionTemplate().selectOne(NAMESPACE + "queryAccountDetailTotal", query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountDetailResult> queryAccountDetail(QueryBase query) {
		return (List<AccountDetailResult>) getSqlSessionTemplate().selectList(NAMESPACE + "queryAccountDetail", query);
	}
}
