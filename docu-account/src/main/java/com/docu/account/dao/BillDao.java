package com.docu.account.dao;

import java.util.List;

import com.docu.account.dto.BillDetail;
import com.docu.components.common.QueryBase;

public interface BillDao {
	Integer insertBillDetail(BillDetail bill);
	
	int queryBillDetailsTotal(QueryBase query);
	
	List<BillDetail> queryBillDetail(QueryBase query); 
}
