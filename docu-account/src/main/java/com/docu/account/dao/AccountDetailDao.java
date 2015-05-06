package com.docu.account.dao;

import java.util.List;

import com.docu.account.dto.AccountDetail;
import com.docu.account.dto.AccountDetailCriteria;
import com.docu.components.common.QueryBase;

public interface AccountDetailDao {
	Integer insertAccountDetail(AccountDetail detail);
	
	Integer deleteAccountDetail(String activityId);
	
	String getTotalBalance(String transactionType);
	
	List<AccountDetail> findAccountDetails(AccountDetailCriteria criteria);
	
	int queryAccountDetailTotal(QueryBase query);
	
	List<AccountDetail> queryAccountDetail(QueryBase query); 
}
