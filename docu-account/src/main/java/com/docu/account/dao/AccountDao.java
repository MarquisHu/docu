package com.docu.account.dao;

import java.util.List;

import com.docu.account.dto.AccountBalanceCriteria;
import com.docu.account.dto.AccountBalanceResult;
import com.docu.account.model.Account;
import com.docu.components.common.QueryBase;

public interface AccountDao {
	Integer insertAccount(Account account);
	
	Integer updateAccount(Account account);
	
	Account findAccount(String userId);
	
	String getTotalBalance(AccountBalanceCriteria criteria);
	
	AccountBalanceResult findAccountBalance(String userId);
	
	int queryAccountBalanceTotal(QueryBase query);
	
	List<AccountBalanceResult> queryAccountBalance(QueryBase query);
	
	Long getSequenceUuid(String sequenceName);
}
