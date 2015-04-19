package com.docu.account.service;

import com.docu.account.dto.AccountBalance;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;

public interface AccountBalanceService {
	PageDO<AccountBalance> queryAccountBalance(QueryBase query);
}
