package com.docu.user.dao;

import java.util.List;

import com.docu.components.common.QueryBase;
import com.docu.user.dto.User;

public interface UserDao {
	Long insertUser(User user);
	
	User findUser(String userId);
	
	int queryUserTotal(QueryBase query);
	
	List<User> queryUser(QueryBase query);
}
