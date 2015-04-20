package com.docu.account.dao;

import java.util.List;

import com.docu.account.dto.ChargeAccountDetail;
import com.docu.account.dto.ChargeDetail;
import com.docu.components.common.QueryBase;

public interface ChargeDao {
	ChargeAccountDetail findChargeAccountDetail(String userId);
	
	Integer insertChargeDetail(ChargeDetail charge);
	
	int queryChargeDetailsTotal(QueryBase query);
	
	List<ChargeDetail> queryChargeDetail(QueryBase query); 
}
