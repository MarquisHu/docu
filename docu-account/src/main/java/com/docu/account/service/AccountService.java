package com.docu.account.service;

import com.docu.account.dto.Account;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;


public interface AccountService {
	Long saveAccount(Account account);
	
	Account queryAccount(String userId);
	
	PageDO<Account> queryAccounts(QueryBase query);
}
