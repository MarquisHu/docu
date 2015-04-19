package com.docu.account.service;

import com.docu.account.dto.Account;
import com.docu.account.dto.ChargeAccountDetail;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;


public interface AccountService {
	int saveAccount(Account account);
	
	int updateAccount(Account account);
	
	Account queryAccount(String userId);
	
	ChargeAccountDetail findChargeAccountDetail(String userId);
	
	PageDO<Account> queryAccounts(QueryBase query);
}
