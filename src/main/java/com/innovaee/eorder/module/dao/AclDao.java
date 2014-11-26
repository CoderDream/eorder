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
	public List<Acl> findAcl(String rolename) {
		Acl acl = new Acl();
		acl.setRoleName(rolename);
		return (List<Acl>) super.getHibernateTemplate().findByExample(acl);
	}

	@SuppressWarnings("unchecked")
	public List<Acl> findAllAcls() {
		return (List<Acl>) super.getHibernateTemplate().find("FROM Acl");
	}
}