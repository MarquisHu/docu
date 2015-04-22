package com.docu.user.service;

import com.docu.components.common.PageDO;
import com.docu.components.common.QueryBase;
import com.docu.user.dto.User;


public interface UserService {
	Long saveUser(User user);
	
	User queryUser(String userId);
	
	PageDO<User> queryUser(QueryBase query);
}
