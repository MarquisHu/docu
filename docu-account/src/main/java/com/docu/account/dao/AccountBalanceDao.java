package com.docu.account.dao;

import java.util.List;

import com.docu.account.dto.AccountBalance;
import com.docu.components.common.QueryBase;

public interface AccountBalanceDao {
	int queryAccountBalanceTotal(QueryBase query);
	
	List<AccountBalance> queryAccountBalance(QueryBase query);
}
