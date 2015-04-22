package com.docu.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.docu.account.dao.BillDao;
import com.docu.account.dto.BillDetail;
import com.docu.account.service.BillService;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;

public class BillServiceImpl implements BillService {
	@Resource
	private BillDao billDao;
	
	@Override
	public int saveBillDetail(BillDetail bill) {
		return billDao.insertBillDetail(bill);
	}

	@Override
	public PageDO<BillDetail> queryBillDetail(QueryBase query) {
		query.setTotal(billDao.queryBillDetailsTotal(query));
		List<BillDetail> bills = billDao.queryBillDetail(query);
		PageDO<BillDetail> page = new PageDO<BillDetail>(query.getPageNum(), query.getPageSize(), query.getTotal());
        page.setRows(bills);
        return page;
	}
}
