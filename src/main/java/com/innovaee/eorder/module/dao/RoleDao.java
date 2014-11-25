package com.innovaee.eorder.module.dao;

import java.util.List;

import com.innovaee.eorder.module.entity.Role;

public class RoleDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return Role.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> findRolesByName(String rolename) {
		Role role = new Role();
		role.setRoleName(rolename);
		return (List<Role>)super.getHibernateTemplate().findByExample(role);
	}
}
