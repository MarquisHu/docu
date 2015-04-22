package com.docu.account.dao.impl;

import java.util.List;

import com.docu.account.dao.BillDao;
import com.docu.account.dto.BillDetail;
import com.docu.components.common.BaseDao;
import com.docu.components.common.QueryBase;

public class IbatisBillDao extends BaseDao implements BillDao {
	private static final String NAMESPACE = "com.docu.account.dao.BillDao.";
	
	@Override
	public Integer insertBillDetail(BillDetail bill) {
		return getSqlSessionTemplate().insert(NAMESPACE + "insertBillDetail", bill);
	}

	@Override
	public int queryBillDetailsTotal(QueryBase query) {
		return (Integer) getSqlSessionTemplate().selectOne(NAMESPACE + "queryBillDetailsTotal", query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BillDetail> queryBillDetail(QueryBase query) {
		return (List<BillDetail>) getSqlSessionTemplate().selectList(NAMESPACE + "queryBillDetail", query);
	}
}
