package com.innovaee.eorder.module.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import com.innovaee.eorder.module.dao.RoleDao;
import com.innovaee.eorder.module.dao.UserDao;
import com.innovaee.eorder.module.dao.UserRoleDao;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.entity.UserRole;
import com.innovaee.eorder.module.utils.Constants;
import com.innovaee.eorder.module.utils.StringUtil;

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

	// public List<UserRole> findUserRolesByRoleId(Integer roleId) {
	// return userRoleDao.findUserRolesByRoleId(roleId);
	// }

	public List<UserRole> findUserRolesByUserId(Integer userId) {
		return userRoleDao.findUserRolesByUserId(userId);
	}

	public UserRole findUserRoleByIds(Integer userId, Integer roleId) {
		return userRoleDao.findUserRoleByIds(userId, roleId);
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
	 * 给用户的添加某个角色
	 * 
	 * @param role
	 *            待移除的角色
	 * @param user
	 *            待修改角色的用户
	 */
	public UserRole saveUserRole(User user, Role role) {
		UserRole rtnUserRole = null;
		User userDB = (User) userDao.get(user.getUserId());
		Role roleDB = (Role) roleDao.get(role.getRoleId());
		UserRole userRole = null;
		if (null != roleDB && null != userDB) {
			Integer userId = userDB.getUserId();
			Integer roleId = roleDB.getRoleId();

			Timestamp createAt = Timestamp.valueOf(new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
					.getTime()));
			userRole = new UserRole(userId, roleId, createAt);
			UserRole userRoleDB = (UserRole) userRoleDao.findUserRoleByIds(
					userId, roleId);

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
	public void removeUserRole(User user, Role role) {
		User userDB = (User) userDao.get(user.getUserId());
		Role roleDB = (Role) roleDao.get(role.getRoleId());
		// UserRole userRole = null;
		if (null != roleDB && null != userDB) {
			Integer roleId = roleDB.getRoleId();
			Integer userId = userDB.getUserId();
			UserRole userRoleDB = (UserRole) userRoleDao.findUserRoleByIds(
					userId, roleId);
			// 如果DB存在，就删除
			if (null != userRoleDB) {
				userRoleDao.removeUserRole(userRoleDB);
			}
		}
	}

	/**
	 * @param roleId
	 * @return
	 */
	public List<Role> findLeftRolesByUserId(Integer userId) {
		List<Role> roles = new ArrayList<Role>();
		List<UserRole> userRoles = userRoleDao.findUserRolesByUserId(userId);
		Role role = null;
		for (UserRole userRole : userRoles) {
			role = roleDao.loadRole(userRole.getRoleId());
			roles.add(role);
		}

		List<Role> allRoles = roleDao.findAllRoles();
		allRoles.removeAll(roles);

		return allRoles;
	}

	public void updateUserRole(Integer userId, String myRoleIds) {
		List<Integer> myRoleIdList = StringUtil.stringToIntegerList(myRoleIds,
				Constants.REGEX);

		// 1. 根据userId获取DB中的roleId列表；
		List<UserRole> dbUserRoles = userRoleDao.findUserRolesByRoleId(userId);
		List<Integer> dbRoleIds = new ArrayList<Integer>();
		for (UserRole userRole : dbUserRoles) {
			dbRoleIds.add(userRole.getRoleId());
		}

		// 2. 取得需要新增的roleId列表；
		List<Integer> toAddRoleIds = new ArrayList<Integer>();
		toAddRoleIds.addAll(myRoleIdList);
		toAddRoleIds.removeAll(dbRoleIds);
		for (Integer roleId : toAddRoleIds) {
			saveUserRole(new User(userId), new Role(roleId));
		}

		// 3. 取得需要删除的functionId列表；
		List<Integer> toDeleteRoleIds = new ArrayList<Integer>();
		toDeleteRoleIds.addAll(dbRoleIds);
		toDeleteRoleIds.removeAll(myRoleIdList);
		for (Integer roleId : toDeleteRoleIds) {
			removeUserRole(new User(userId), new Role(roleId));
		}
	}
}