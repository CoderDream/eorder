package com.innovaee.eorder.module.dao;

import java.util.List;

import com.innovaee.eorder.module.entity.Acl;

public class AclDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return Acl.class;
	}

	@SuppressWarnings("unchecked")
	public List<Acl> findAllAcls() {
		return (List<Acl>) super.getHibernateTemplate().find("FROM Acl");
	}

	public Acl saveAcl(Acl acl) {
		return (Acl)save(acl);
	}

	public void updateAcl(Acl acl) {
		update(acl);
	}

	public void removeAcl(Acl acl) {
		super.getHibernateTemplate().delete(acl);
	}

	@SuppressWarnings("unchecked")
	public Acl findAclByRoleFunction(String roleFunction) {
		List<Acl> list = (List<Acl>) super.getHibernateTemplate().find("FROM Acl f WHERE f.roleFunction=?", roleFunction);
		if (null != list && 0 < list.size()) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Acl> findAclsByRoleName(String roleName) {
		List<Acl> list = (List<Acl>) super.getHibernateTemplate().find("FROM Acl f WHERE f.roleName=?", roleName);
		return list;
	}
}