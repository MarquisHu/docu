package com.docu.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.docu.account.dao.AccountDao;
import com.docu.account.dto.Account;
import com.docu.account.service.AccountService;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;

public class AccountServiceImpl implements AccountService {
	@Resource
	private AccountDao accountDao;
	
	@Override
	public Long saveAccount(Account account) {
		return accountDao.insertAccount(account);
	}

	@Override
	public Account queryAccount(String userId) {
		return accountDao.findAccount(userId);
	}

	@Override
	public PageDO<Account> queryAccounts(QueryBase query) {
		query.setTotal(accountDao.queryAccountTotal(query));
		List<Account> accounts = accountDao.queryAccount(query);
		PageDO<Account> page = new PageDO<Account>(query.getPageNum(), query.getPageSize(), query.getTotal());
        page.setRows(accounts);
        return page;
	}
}
