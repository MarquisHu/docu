package com.docu.account.service;

import com.docu.account.dto.AccountDetail;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;


public interface AccountDetailService {
	int saveAccountDetail(AccountDetail detail);
	
	PageDO<AccountDetail> queryAccountDetails(QueryBase query);
}
