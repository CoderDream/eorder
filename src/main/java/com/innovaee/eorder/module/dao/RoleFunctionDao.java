package com.innovaee.eorder.module.dao;

import java.util.List;

import com.innovaee.eorder.module.entity.RoleFunction;

public class RoleFunctionDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return RoleFunction.class;
	}

	@SuppressWarnings("unchecked")
	public List<RoleFunction> findAllRoleFunctions() {
		return (List<RoleFunction>) super.getHibernateTemplate().find(
				"FROM RoleFunction");
	}

	public RoleFunction saveRoleFunction(RoleFunction roleFunction) {
		return (RoleFunction) save(roleFunction);
	}

	public void removeRoleFunction(RoleFunction roleFunction) {
		super.getHibernateTemplate().delete(roleFunction);
	}

	@SuppressWarnings("unchecked")
	public List<RoleFunction> findRoleFunctionsByRoleId(Integer roleId) {
		List<RoleFunction> list = (List<RoleFunction>) super
				.getHibernateTemplate().find(
						"FROM RoleFunction rf WHERE rf.roleId=?", roleId);
		return list;
	}

	@SuppressWarnings("unchecked")
	public RoleFunction findRoleFunctionByIds(Integer roleId, Integer functionId) {
		List<RoleFunction> list = (List<RoleFunction>) super
				.getHibernateTemplate()
				.find("FROM RoleFunction rf WHERE rf.roleId=? and rf.functionId=?",
						roleId, functionId);
		if (null != list && 0 < list.size()) {
			return list.get(0);
		}
		return null;
	}

}