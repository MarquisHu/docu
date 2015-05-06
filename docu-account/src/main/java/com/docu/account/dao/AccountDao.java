package com.docu.account.dao;

import java.util.List;

import com.docu.account.dto.Account;
import com.docu.account.dto.AccountBalance;
import com.docu.components.common.QueryBase;

public interface AccountDao {
	Integer insertAccount(Account account);
	
	Integer updateAccount(Account account);
	
	Account findAccount(String userId);
	
	String getTotalBalance();
	
	AccountBalance findAccountBalance(String userId);
	
	int queryAccountBalanceTotal(QueryBase query);
	
	List<AccountBalance> queryAccountBalance(QueryBase query);
	
	Long getSequenceUuid(String sequenceName);
}
