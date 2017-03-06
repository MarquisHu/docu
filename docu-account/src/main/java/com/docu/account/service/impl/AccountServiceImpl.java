package com.docu.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.docu.account.dao.AccountDao;
import com.docu.account.dto.AccountBalanceCriteria;
import com.docu.account.dto.AccountBalanceResult;
import com.docu.account.model.Account;
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
	public String getTotalBalance(AccountBalanceCriteria criteria) {
		return accountDao.getTotalBalance(criteria);
	}

	@Override
	public AccountBalanceResult queryAccountBalance(String userId) {
		return accountDao.findAccountBalance(userId);
	}
	
	@Override
	public PageDO<AccountBalanceResult> queryAccountBalance(QueryBase query) {
		query.setTotal(accountDao.queryAccountBalanceTotal(query));
		List<AccountBalanceResult> balances = accountDao.queryAccountBalance(query);
		PageDO<AccountBalanceResult> page = new PageDO<AccountBalanceResult>(query.getPageNum(), query.getPageSize(), query.getTotal());
        page.setRows(balances);
        return page;
	}
	
	@Override
	public long getSequenceUuid(String sequenceName) {
		return accountDao.getSequenceUuid(sequenceName);
	}
}
