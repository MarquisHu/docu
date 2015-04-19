package com.docu.web.home.screen.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.webx.WebxException;
import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;
import com.docu.user.dto.DocumentUser;
import com.docu.user.service.DocumentUserService;

public class Index {
	@Autowired
	private DocumentUserService userService;
	
	public void execute(TurbineRunData rundata, Context context) throws WebxException {
		HttpSession session = rundata.getRequest().getSession();
		String systemUserId = (String) session.getAttribute("systemUserId");
		if(systemUserId != null && systemUserId.length() != 0){
			DocumentUser entity = new DocumentUser();
			int pageNum = rundata.getParameters().getInt("pageNum");
			String criteria = rundata.getParameters().getString("userId");
			entity.setUserId(criteria);
			
			QueryBase query = new QueryBase(pageNum, entity);
			PageDO<DocumentUser> page = userService.queryDocumentUser(query);
			
			context.put("page", page);
			context.put("userId", criteria);
			context.put("systemUserId", systemUserId);
		}
	}
}
