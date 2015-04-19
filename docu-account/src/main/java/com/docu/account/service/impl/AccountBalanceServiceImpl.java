package com.docu.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.docu.account.dao.AccountBalanceDao;
import com.docu.account.dto.AccountBalance;
import com.docu.account.service.AccountBalanceService;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;

public class AccountBalanceServiceImpl implements AccountBalanceService {
	@Resource
	private AccountBalanceDao balanceDao;
	
	@Override
	public PageDO<AccountBalance> queryAccountBalance(QueryBase query) {
		query.setTotal(balanceDao.queryAccountBalanceTotal(query));
		List<AccountBalance> balances = balanceDao.queryAccountBalance(query);
		PageDO<AccountBalance> page = new PageDO<AccountBalance>(query.getPageNum(), query.getPageSize(), query.getTotal());
        page.setRows(balances);
        return page;
	}
}
