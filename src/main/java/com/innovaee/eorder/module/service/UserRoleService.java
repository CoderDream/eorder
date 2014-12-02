package com.innovaee.eorder.module.service;

import java.util.List;

import javax.annotation.Resource;

import com.innovaee.eorder.module.dao.RoleDao;
import com.innovaee.eorder.module.dao.UserDao;
import com.innovaee.eorder.module.dao.UserRoleDao;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.entity.UserRole;

public class UserRoleService extends BaseService {

	@Resource
	private UserRoleDao userRoleDao;

	@Resource
	private RoleDao roleDao;

	@Resource
	private UserDao userDao;

	/**
	 * 查找所有的用户角色关系记录
	 * 
	 * @return 所有的用户角色关系记录
	 */
	public List<UserRole> findAllUserRoles() {
		return (List<UserRole>) userRoleDao.findAllUserRoles();
	}

	/**
	 * 
	 * @param userRole
	 * @return
	 */
	public UserRole findUserRoleByUserRole(String userRole) {
		return (UserRole) userRoleDao.findUserRoleByUserRole(userRole);
	}

	public List<UserRole> findUserRolesByRoleName(String roleName) {
		return userRoleDao.findUserRolesByRoleName(roleName);
	}

	public List<UserRole> findUserRolesByUsername(String userName) {
		return userRoleDao.findUserRolesByUsername(userName);
	}

	/**
	 * @param userRole
	 */
	public void saveUserRole(UserRole userRole) {
		userRoleDao.saveUserRole(userRole);
	}

	/**
	 * @param userRole
	 */
	public void updateUserRole(UserRole userRole) {
		userRoleDao.updateUserRole(userRole);
	}

	/**
	 * 根据用户角色名称移除用户角色关系记录
	 * 
	 * @param userRoleName
	 *            用户角色名称
	 */
	public void removeUserRole(String userRoleName) {
		userRoleDao.removeUserRole(new UserRole(userRoleName));
	}

	/**
	 * 根据用户角色名称数组移除对应用户角色关系记录
	 * 
	 * @param userRoleName
	 *            用户角色名称数组
	 */
	public void removeUserRoles(String[] userRoleName) {
		int length = userRoleName.length;
		for (int i = 0; i < length; i++) {
			userRoleDao.removeUserRole(new UserRole(userRoleName[i]));
		}

	}

	/**
	 * 给用户的添加某个角色
	 * 
	 * @param role
	 *            待移除的角色
	 * @param user
	 *            待修改角色的用户
	 */
	public UserRole saveUserRole(Role role, User user) {
		UserRole rtnUserRole = null;
		Role roleDB = (Role) roleDao.get(role.getRoleName());
		User userDB = (User) userDao.get(user.getUserName());
		UserRole userRole = null;
		if (null != roleDB && null != userDB) {
			String roleUser = roleDB.getRoleName() + userDB.getUserName();
			String roleName = roleDB.getRoleName();
			String userName = userDB.getUserName();
			userRole = new UserRole(roleUser, userName, roleName);
			UserRole userRoleDB = (UserRole) userRoleDao.get(roleUser);
			// 如果DB不存在，就添加
			if (null == userRoleDB) {
				rtnUserRole = userRoleDao.saveUserRole(userRole);
			}
		}

		return rtnUserRole;

	}

	/**
	 * 将用户的某个角色移除
	 * 
	 * @param role
	 *            待移除的角色
	 * @param user
	 *            待修改角色的用户
	 */
	public void removeUserRole(Role role, User user) {
		Role roleDB = (Role) roleDao.get(role.getRoleName());
		User userDB = (User) userDao.get(user.getUserName());
		// UserRole userRole = null;
		if (null != roleDB && null != userDB) {
			String roleUser = roleDB.getRoleName() + userDB.getUserName();
			// String roleName = roleDB.getRoleName();
			// String userName = userDB.getUserName();
			// userRole = new UserRole(roleUser, roleName, userName);
			UserRole userRoleDB = (UserRole) userRoleDao.get(roleUser);
			// 如果DB不存在，就添加
			if (null != userRoleDB) {
				userRoleDao.removeUserRole(userRoleDB);
			}
		}

	}
}