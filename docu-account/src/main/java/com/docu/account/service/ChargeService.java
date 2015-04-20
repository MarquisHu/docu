package com.docu.account.service;

import com.docu.account.dto.ChargeAccountDetail;
import com.docu.account.dto.ChargeDetail;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;

public interface ChargeService {
	int saveChargeDetail(ChargeDetail charge);
	
	ChargeAccountDetail findChargeAccountDetail(String userId);
	
	PageDO<ChargeDetail> queryChargeDetail(QueryBase query);
}
