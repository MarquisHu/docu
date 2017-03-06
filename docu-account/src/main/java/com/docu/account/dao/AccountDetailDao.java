package com.docu.account.dao;

import java.util.List;

import com.docu.account.dto.AccountBalanceCriteria;
import com.docu.account.dto.AccountDetailCriteria;
import com.docu.account.dto.AccountDetailResult;
import com.docu.account.dto.ChargeDetailResult;
import com.docu.account.model.AccountDetail;
import com.docu.account.model.ChargeDetail;
import com.docu.components.common.QueryBase;

public interface AccountDetailDao {
	Integer insertAccountDetail(AccountDetail detail);
	
	Integer insertChargeDetail(ChargeDetail detail);
	
	Integer updateChargeDetail(String chargeId);
	
	Integer deleteAccountDetail(String activityId);
	
	Integer deleteChargeDetail(String chargeId);
	
	ChargeDetail findChargeDetail(String chargeId);
	
	String getTotalBalance(AccountBalanceCriteria criteria);
	
	List<AccountDetail> findAccountDetails(AccountDetailCriteria criteria);
	
	int queryAccountDetailTotal(QueryBase query);
	
	List<AccountDetailResult> queryAccountDetail(QueryBase query); 
	
	int queryChargeDetailTotal(QueryBase query);
	
	List<ChargeDetailResult> queryChargeDetail(QueryBase query); 
}
