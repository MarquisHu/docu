package com.docu.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.docu.account.dao.AccountDao;
import com.docu.account.dto.Account;
import com.docu.account.dto.AccountBalance;
import com.docu.account.service.AccountService;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;

public class AccountServiceImpl implements AccountService {
	@Resource
	private AccountDao accountDao;
	
	@Override
	public int saveAccount(Account account) {
		return accountDao.insertAccount(account);
	}
	
	@Override
	public int updateAccount(Account account) {
		return accountDao.updateAccount(account);
	}

	@Override
	public Account queryAccount(String userId) {
		return accountDao.findAccount(userId);
	}
	
	@Override
	public AccountBalance queryAccountBalance(String userId) {
		return accountDao.findAccountBalance(userId);
	}
	
	@Override
	public PageDO<AccountBalance> queryAccountBalance(QueryBase query) {
		query.setTotal(accountDao.queryAccountBalanceTotal(query));
		List<AccountBalance> balances = accountDao.queryAccountBalance(query);
		PageDO<AccountBalance> page = new PageDO<AccountBalance>(query.getPageNum(), query.getPageSize(), query.getTotal());
        page.setRows(balances);
        return page;
	}
	
	@Override
	public long getSequenceUuid(String sequenceName) {
		return accountDao.getSequenceUuid(sequenceName);
	}
}
