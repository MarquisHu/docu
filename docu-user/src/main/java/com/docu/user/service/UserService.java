package com.docu.user.service;

import java.util.List;

import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;
import com.docu.user.dto.User;


public interface UserService {
	int saveUser(User user);
	
	int updateUser(User user);
	
	List<User> findUsers();
	
	User queryUser(String userId);
	
	PageDO<User> queryUser(QueryBase query);
}
