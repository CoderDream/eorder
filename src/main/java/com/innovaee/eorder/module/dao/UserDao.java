package com.innovaee.eorder.module.dao;

import javax.annotation.Resource;

import com.innovaee.eorder.module.entity.User;

public class UserDao extends BaseDao {

	@Resource
	private UserDao userDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return User.class;
	}
	
	public User getUserByPassword(String username, String password) {
		User user = (User)userDao.get(username);
		
		if (null == user) {
			return null;
		}
		
		return (0 == password.compareTo(user.getUserPassword() ) ) ? user : null;
	}
}
