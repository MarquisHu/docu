package com.docu.user.dao;

import java.util.List;

import com.docu.components.common.QueryBase;
import com.docu.user.dto.DocumentUser;

public interface DocumentUserDao {
	Long insertUser(DocumentUser user);
	
	DocumentUser findUser(String userId);
	
	int queryDocumentUserTotal(QueryBase query);
	
	List<DocumentUser> queryDocumentUser(QueryBase query);
}
