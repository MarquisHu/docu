package com.docu.web.home.action.acct;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.uribroker.URIBrokerService;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.docu.account.dto.Account;
import com.docu.account.dto.ChargeDetail;
import com.docu.account.service.AccountService;
import com.docu.account.service.ChargeService;
import com.docu.web.common.context.EnvUtils;

public class AccountAction {
	
	@Autowired
	private URIBrokerService uriBrokerService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ChargeService chargeService;
	
	public void doSaveCharge(TurbineRunData rundata, Context context){
		HttpSession session = rundata.getRequest().getSession();
		String systemUserId = (String) session.getAttribute("systemUserId");
		if (systemUserId == null) {
			rundata.setRedirectLocation(EnvUtils.getContextPath() + "/index.htm");
			return;
		}
		String userId = rundata.getParameters().getString("userId");
		String accountId = rundata.getParameters().getString("accountId");
		Float recvAmount = rundata.getParameters().getFloat("recvAmount");
		Integer percent = rundata.getParameters().getInt("percent");
		
		Account account = accountService.queryAccount(userId);
		if (account != null) {
			Float privateAmount = (recvAmount * percent) / 100;
			Float commontAmount = recvAmount - privateAmount;
			Date date = new Date();
			
			account.setUserId(userId);
			account.setAccountId(accountId);
			account.setBalanceAmount(account.getBalanceAmount() + recvAmount);
			account.setPrivateAmount(account.getPrivateAmount() + privateAmount);
			account.setCommonAmount(account.getCommonAmount() + commontAmount);
			account.setUpdateBy(systemUserId);
			account.setUpdateTime(date);
			
			int result = accountService.updateAccount(account);
			if (result == 1) {
				ChargeDetail charge = new ChargeDetail();
				charge.setUserId(userId);
				charge.setAccountId(accountId);
				charge.setRecvAmount(recvAmount);
				charge.setRecvTime(date);
				charge.setPercent(percent);
				charge.setUpdateBy(userId);
				charge.setUpdateTime(date);
				
				result = chargeService.saveChargeDetail(charge);
				if (result == 1) {
					rundata.setRedirectLocation(EnvUtils.getContextPath()+"/acct/index.htm?userId=" + userId);
				} else {
					context.put("msg", "充值失败");
				}
			} else {
				context.put("msg", "充值失败");
			}
		} else {
			context.put("msg", "充值失败");
		}
	}
}
