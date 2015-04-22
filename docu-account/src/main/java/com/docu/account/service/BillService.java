package com.docu.account.service;

import com.docu.account.dto.BillDetail;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;

public interface BillService {
	int saveBillDetail(BillDetail bill);
	
	PageDO<BillDetail> queryBillDetail(QueryBase query);
}
