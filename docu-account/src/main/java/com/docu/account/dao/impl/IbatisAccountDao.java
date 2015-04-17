package com.docu.account.dao.impl;

import com.docu.account.dao.AccountDao;
import com.docu.account.dto.Account;
import com.docu.components.common.BaseDao;

public class IbatisAccountDao extends BaseDao implements AccountDao {
	private static final String NAMESPACE = "com.docu.account.dao.AccountDao.";
	
	@Override
	public Long insertAccount(Account account) {
		return (long) getSqlSessionTemplate().insert(NAMESPACE + "insertAccount", account);
	}
}
