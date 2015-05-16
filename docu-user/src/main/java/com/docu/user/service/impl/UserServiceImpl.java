package com.docu.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;
import com.docu.user.dao.UserDao;
import com.docu.user.dto.UserResult;
import com.docu.user.model.User;
import com.docu.user.service.UserService;

public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	
	@Override
	public int saveUser(User user) {
		return userDao.insertUser(user);
	}
	
	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public List<UserResult> findUsers() {
		return userDao.findUsers();
	}

	@Override
	public User queryUser(String userId) {
		return userDao.findUser(userId);
	}

	@Override
	public PageDO<User> queryUser(QueryBase query) {
		query.setTotal(userDao.queryUserTotal(query));
		List<User> users = userDao.queryUser(query);
		PageDO<User> page = new PageDO<User>(query.getPageNum(), query.getPageSize(), query.getTotal());
        page.setRows(users);
        return page;
	}
}
