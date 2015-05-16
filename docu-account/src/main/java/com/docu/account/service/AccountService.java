package com.docu.account.service;

import com.docu.account.dto.AccountBalanceCriteria;
import com.docu.account.dto.AccountBalanceResult;
import com.docu.account.model.Account;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;


public interface AccountService {
	int saveAccount(Account account);
	
	int updateAccount(Account account);
	
	Account queryAccount(String userId);
	
	String getTotalBalance(AccountBalanceCriteria criteria);
	
	AccountBalanceResult queryAccountBalance(String userId);
	
	PageDO<AccountBalanceResult> queryAccountBalance(QueryBase query);
	
	long getSequenceUuid(String sequenceName);
}
