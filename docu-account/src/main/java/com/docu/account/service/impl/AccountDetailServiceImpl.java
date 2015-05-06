package com.docu.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.docu.account.dao.AccountDetailDao;
import com.docu.account.dto.AccountDetail;
import com.docu.account.dto.AccountDetailCriteria;
import com.docu.account.service.AccountDetailService;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;

public class AccountDetailServiceImpl implements AccountDetailService {
	
	@Resource
	private AccountDetailDao detailDao;
	
	@Override
	public int saveAccountDetail(AccountDetail detail) {
		return detailDao.insertAccountDetail(detail);
	}

	@Override
	public int deleteAccountDetail(String activityId) {
		return detailDao.deleteAccountDetail(activityId);
	}
	
	@Override
	public String getTotalBalance(String transactionType) {
		return detailDao.getTotalBalance(transactionType);
	}

	@Override
	public List<AccountDetail> findAccountDetails(AccountDetailCriteria criteria) {
		return detailDao.findAccountDetails(criteria);
	}

	@Override
	public PageDO<AccountDetail> queryAccountDetails(QueryBase query) {
		query.setTotal(detailDao.queryAccountDetailTotal(query));
		List<AccountDetail> details = detailDao.queryAccountDetail(query);
		PageDO<AccountDetail> page = new PageDO<AccountDetail>(query.getPageNum(), query.getPageSize(), query.getTotal());
        page.setRows(details);
        return page;
	}
}
