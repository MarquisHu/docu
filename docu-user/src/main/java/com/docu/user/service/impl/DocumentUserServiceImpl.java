package com.docu.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;
import com.docu.user.dao.DocumentUserDao;
import com.docu.user.dto.DocumentSystemUser;
import com.docu.user.dto.DocumentUser;
import com.docu.user.service.DocumentUserService;

public class DocumentUserServiceImpl implements DocumentUserService {
	@Resource
	private DocumentUserDao userDao;
	
	@Override
	public DocumentSystemUser querySystemUser(String userId) {
		return userDao.findSystemUser(userId);
	}
	
	@Override
	public Long saveUser(DocumentUser user) {
		return userDao.insertUser(user);
	}

	@Override
	public DocumentUser queryUser(String userId) {
		return userDao.findUser(userId);
	}

	@Override
	public PageDO<DocumentUser> queryDocumentUser(QueryBase query) {
		query.setTotal(userDao.queryDocumentUserTotal(query));
		List<DocumentUser> users = userDao.queryDocumentUser(query);
		PageDO<DocumentUser> page = new PageDO<DocumentUser>(query.getPageNum(), query.getPageSize(), query.getTotal());
        page.setRows(users);
        return page;
	}
}
