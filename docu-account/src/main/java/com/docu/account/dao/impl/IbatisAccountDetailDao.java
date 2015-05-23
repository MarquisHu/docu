package com.docu.account.dao.impl;

import java.util.List;

import com.docu.account.dao.AccountDetailDao;
import com.docu.account.dto.AccountBalanceCriteria;
import com.docu.account.dto.AccountDetailCriteria;
import com.docu.account.dto.AccountDetailResult;
import com.docu.account.dto.ChargeDetailResult;
import com.docu.account.model.AccountDetail;
import com.docu.account.model.ChargeDetail;
import com.docu.components.common.BaseDao;
import com.docu.components.common.QueryBase;

public class IbatisAccountDetailDao extends BaseDao implements AccountDetailDao {
	private static final String NAMESPACE = "com.docu.account.dao.AccountDetailDao.";
	
	@Override
	public Integer insertAccountDetail(AccountDetail detail) {
		return getSqlSessionTemplate().insert(NAMESPACE + "insertAccountDetail", detail);
	}

	@Override
	public Integer insertChargeDetail(ChargeDetail detail) {
		return getSqlSessionTemplate().insert(NAMESPACE + "insertChargeDetail", detail);
	}

	@Override
	public Integer updateChargeDetail(String chargeId) {
		return getSqlSessionTemplate().update(NAMESPACE + "updateChargeDetail", chargeId);
	}

	@Override
	public Integer deleteAccountDetail(String activityId) {
		return getSqlSessionTemplate().delete(NAMESPACE + "deleteAccountDetail", activityId);
	}

	@Override
	public Integer deleteChargeDetail(String chargeId) {
		return getSqlSessionTemplate().delete(NAMESPACE + "deleteChargeDetail", chargeId);
	}
	
	@Override
	public ChargeDetail findChargeDetail(String chargeId) {
		return (ChargeDetail) getSqlSessionTemplate().selectOne(NAMESPACE + "findChargeDetail", chargeId);
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

	@Override
	public int queryChargeDetailTotal(QueryBase query) {
		return (Integer) getSqlSessionTemplate().selectOne(NAMESPACE + "queryChargeDetailTotal", query);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ChargeDetailResult> queryChargeDetail(QueryBase query) {
		return (List<ChargeDetailResult>) getSqlSessionTemplate().selectList(NAMESPACE + "queryChargeDetail", query);
	}
}
