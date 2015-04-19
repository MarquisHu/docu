package com.docu.user.service;

import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;
import com.docu.user.dto.DocumentSystemUser;
import com.docu.user.dto.DocumentUser;


public interface DocumentUserService {
	DocumentSystemUser querySystemUser(String userId);
	
	Long saveUser(DocumentUser user);
	
	DocumentUser queryUser(String userId);
	
	PageDO<DocumentUser> queryDocumentUser(QueryBase query);
}
