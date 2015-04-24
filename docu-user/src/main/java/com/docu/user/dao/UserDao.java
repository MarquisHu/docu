package com.docu.user.dao;

import java.util.List;

import com.docu.components.common.QueryBase;
import com.docu.user.dto.User;

public interface UserDao {
	Integer insertUser(User user);
	
	Integer updateUser(User user);
	
	User findUser(String userId);
	
	List<User> findUsers();
	
	int queryUserTotal(QueryBase query);
	
	List<User> queryUser(QueryBase query);
}
