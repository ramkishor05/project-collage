package org.brijframework.college.dao;

import java.util.List;

import org.brijframework.college.model.User;

public interface UserDao extends DAO<Integer, User> {

	public User getUserByUsername(String username);
	public User getUserByUsername(String username,String password,int retryCount);

	List<User> searchUsertByName(String userName);
	User getUserByPassword(String password);
	User checkUserByUserName(String userName, String password);

}
