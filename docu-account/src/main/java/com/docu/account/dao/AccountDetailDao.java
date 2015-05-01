package com.docu.account.dao;

import java.util.List;

import com.docu.account.dto.AccountDetail;
import com.docu.components.common.QueryBase;

public interface AccountDetailDao {
	Integer insertAccountDetail(AccountDetail detail);
	
	int queryAccountDetailTotal(QueryBase query);
	
	List<AccountDetail> queryAccountDetail(QueryBase query); 
}
