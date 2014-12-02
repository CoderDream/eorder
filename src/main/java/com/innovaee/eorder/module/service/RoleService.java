package com.innovaee.eorder.module.service;

import java.util.List;

import javax.annotation.Resource;

import com.innovaee.eorder.module.dao.RoleDao;
import com.innovaee.eorder.module.entity.Role;

public class RoleService extends BaseService {

	@Resource
	private RoleDao roleDao;

	public List<Role> findAllRoles() {
		return (List<Role>) roleDao.findAllRoles();
	}

	public Role loadRole(Integer roleId) {
		return (Role) roleDao.get(roleId);
	}

	public Role findRolesByRoleName(String roleName) {
		return (Role) roleDao.findRolesByRoleName(roleName);
	}

	public Role saveRole(Role role) {
		return roleDao.saveRole(role);
	}

	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	public void removeRole(Integer roleId) {
		roleDao.removeRole(new Role(roleId));
	}

	public void removeRoles(String[] roleIds) {
		int length = roleIds.length;
		for (int i = 0; i < length; i++) {
			roleDao.removeRole(new Role(Integer.parseInt(roleIds[i])));
		}
	}
}