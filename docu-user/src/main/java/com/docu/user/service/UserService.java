package com.docu.user.service;

import java.util.List;

import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;
import com.docu.user.dto.UserResult;
import com.docu.user.model.User;


public interface UserService {
	int saveUser(User user);
	
	int updateUser(User user);
	
	List<UserResult> findUsers();
	
	User queryUser(String userId);
	
	PageDO<User> queryUser(QueryBase query);
}
