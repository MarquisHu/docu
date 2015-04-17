package com.docu.account.service.impl;

import javax.annotation.Resource;

import com.docu.account.dao.AccountDao;
import com.docu.account.dto.Account;
import com.docu.account.service.AccountService;

public class AccountServiceImpl implements AccountService {
	@Resource
	private AccountDao accountDao;
	
	@Override
	public Long saveAccount(Account account) {
		return accountDao.insertAccount(account);
	}
}
