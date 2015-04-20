package com.docu.account.dao;

import java.util.List;

import com.docu.account.dto.Account;
import com.docu.components.common.QueryBase;

public interface AccountDao {
	Integer insertAccount(Account account);
	
	Integer updateAccount(Account account);
	
	Account findAccount(String userId);
	
	int queryAccountTotal(QueryBase query);
	
	List<Account> queryAccount(QueryBase query);
}
