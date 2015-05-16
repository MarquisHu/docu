package com.docu.account.service;

import java.util.List;

import com.docu.account.dto.AccountBalanceCriteria;
import com.docu.account.dto.AccountDetailCriteria;
import com.docu.account.dto.AccountDetailResult;
import com.docu.account.model.AccountDetail;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;


public interface AccountDetailService {
	int saveAccountDetail(AccountDetail detail);
	
	int deleteAccountDetail(String activityId);
	
	String getTotalBalance(AccountBalanceCriteria criteria);
	
	List<AccountDetail> findAccountDetails(AccountDetailCriteria criteria);
	
	PageDO<AccountDetailResult> queryAccountDetails(QueryBase query);
}
