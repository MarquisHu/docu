package com.docu.account.service;

import java.util.List;

import com.docu.account.dto.AccountDetail;
import com.docu.account.dto.AccountDetailCriteria;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;


public interface AccountDetailService {
	int saveAccountDetail(AccountDetail detail);
	
	int deleteAccountDetail(String activityId);
	
	String getTotalBalance(String transactionType);
	
	List<AccountDetail> findAccountDetails(AccountDetailCriteria criteria);
	
	PageDO<AccountDetail> queryAccountDetails(QueryBase query);
}
