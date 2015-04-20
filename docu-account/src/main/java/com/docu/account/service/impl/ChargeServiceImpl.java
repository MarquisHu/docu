package com.docu.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.docu.account.dao.ChargeDao;
import com.docu.account.dto.ChargeAccountDetail;
import com.docu.account.dto.ChargeDetail;
import com.docu.account.service.ChargeService;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;

public class ChargeServiceImpl implements ChargeService {
	@Resource
	private ChargeDao chargeDao;
	
	@Override
	public int saveChargeDetail(ChargeDetail charge) {
		return chargeDao.insertChargeDetail(charge);
	}

	@Override
	public ChargeAccountDetail findChargeAccountDetail(String userId) {
		return chargeDao.findChargeAccountDetail(userId);
	}

	@Override
	public PageDO<ChargeDetail> queryChargeDetail(QueryBase query) {
		query.setTotal(chargeDao.queryChargeDetailsTotal(query));
		List<ChargeDetail> charges = chargeDao.queryChargeDetail(query);
		PageDO<ChargeDetail> page = new PageDO<ChargeDetail>(query.getPageNum(), query.getPageSize(), query.getTotal());
        page.setRows(charges);
        return page;
	}
}
