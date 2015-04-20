package com.docu.account.service;

import com.docu.account.dto.Account;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;


public interface AccountService {
	int saveAccount(Account account);
	
	int updateAccount(Account account);
	
	Account queryAccount(String userId);
	
	PageDO<Account> queryAccounts(QueryBase query);
}
