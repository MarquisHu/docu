package com.docu.account.service;

import com.docu.account.dto.Account;
import com.docu.account.dto.AccountBalance;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;


public interface AccountService {
	int saveAccount(Account account);
	
	int updateAccount(Account account);
	
	Account queryAccount(String userId);
	
	AccountBalance queryAccountBalance(String userId);
	
	PageDO<AccountBalance> queryAccountBalance(QueryBase query);
	
	long getSequenceUuid(String sequenceName);
}
