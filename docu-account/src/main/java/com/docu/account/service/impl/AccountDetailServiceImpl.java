package com.docu.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.docu.account.dao.AccountDetailDao;
import com.docu.account.dto.AccountBalanceCriteria;
import com.docu.account.dto.AccountDetailCriteria;
import com.docu.account.dto.AccountDetailResult;
import com.docu.account.dto.ChargeDetailResult;
import com.docu.account.model.AccountDetail;
import com.docu.account.model.ChargeDetail;
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
	public int saveChargeDetail(ChargeDetail detail) {
		return detailDao.insertChargeDetail(detail);
	}

	@Override
	public int updateChargeDetail(String chargeId) {
		return detailDao.updateChargeDetail(chargeId);
	}

	@Override
	public int deleteAccountDetail(String activityId) {
		return detailDao.deleteAccountDetail(activityId);
	}

	@Override
	public int deleteChargeDetail(String chargeId) {
		return detailDao.deleteChargeDetail(chargeId);
	}
	
	@Override
	public ChargeDetail queryChargeDetail(String chargeId) {
		return detailDao.findChargeDetail(chargeId);
	}

	@Override
	public String getTotalBalance(AccountBalanceCriteria criteria) {
		return detailDao.getTotalBalance(criteria);
	}

	@Override
	public List<AccountDetail> findAccountDetails(AccountDetailCriteria criteria) {
		return detailDao.findAccountDetails(criteria);
	}

	@Override
	public PageDO<AccountDetailResult> queryAccountDetails(QueryBase query) {
		query.setTotal(detailDao.queryAccountDetailTotal(query));
		List<AccountDetailResult> details = detailDao.queryAccountDetail(query);
		PageDO<AccountDetailResult> page = new PageDO<AccountDetailResult>(query.getPageNum(), query.getPageSize(), query.getTotal());
        page.setRows(details);
        return page;
	}

	@Override
	public PageDO<ChargeDetailResult> queryChargeDetails(QueryBase query) {
		query.setTotal(detailDao.queryChargeDetailTotal(query));
		List<ChargeDetailResult> details = detailDao.queryChargeDetail(query);
		PageDO<ChargeDetailResult> page = new PageDO<ChargeDetailResult>(query.getPageNum(), query.getPageSize(), query.getTotal());
        page.setRows(details);
        return page;
	}
}
