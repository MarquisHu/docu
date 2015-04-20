package com.docu.account.dao.impl;

import java.util.List;

import com.docu.account.dao.ChargeDao;
import com.docu.account.dto.ChargeAccountDetail;
import com.docu.account.dto.ChargeDetail;
import com.docu.components.common.BaseDao;
import com.docu.components.common.QueryBase;

public class IbatisChargeDao extends BaseDao implements ChargeDao {
	private static final String NAMESPACE = "com.docu.account.dao.ChargeDao.";
	
	@Override
	public ChargeAccountDetail findChargeAccountDetail(String userId) {
		return (ChargeAccountDetail) getSqlSessionTemplate().selectOne(NAMESPACE + "findChargeAccountDetail", userId);
	}
	
	@Override
	public Integer insertChargeDetail(ChargeDetail charge) {
		return getSqlSessionTemplate().insert(NAMESPACE + "insertChargeDetail", charge);
	}
	
	@Override
	public int queryChargeDetailsTotal(QueryBase query) {
		return (Integer) getSqlSessionTemplate().selectOne(NAMESPACE + "queryChargeDetailsTotal", query);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ChargeDetail> queryChargeDetail(QueryBase query) {
		return (List<ChargeDetail>) getSqlSessionTemplate().selectList(NAMESPACE + "queryChargeDetail", query);
	}
}
