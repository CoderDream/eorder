package com.innovaee.eorder.module.dao;

import java.util.List;

import com.innovaee.eorder.module.entity.UserRole;

public class UserRoleDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return UserRole.class;
	}

	@SuppressWarnings("unchecked")
	public List<UserRole> findUserRole(String username) {
		UserRole userRole = new UserRole();
		userRole.setUserName(username);
		return (List<UserRole>)super.getHibernateTemplate().findByExample(userRole);		
	}
}
