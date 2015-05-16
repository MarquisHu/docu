package com.docu.user.dao;

import java.util.List;

import com.docu.components.common.QueryBase;
import com.docu.user.dto.UserResult;
import com.docu.user.model.User;

public interface UserDao {
	Integer insertUser(User user);
	
	Integer updateUser(User user);
	
	User findUser(String userId);
	
	List<UserResult> findUsers();
	
	int queryUserTotal(QueryBase query);
	
	List<User> queryUser(QueryBase query);
}
