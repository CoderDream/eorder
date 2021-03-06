/***********************************************
 * Filename        : UserLevelDao.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.dao;

import com.innovaee.eorder.module.entity.UserRole;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @Title: UserRoleDao
 * @Description: 用户角色数据访问对象
 *
 * @version V1.0
 */
public class UserRoleDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return UserRole.class;
	}

	/**
	 * 查找所有的用户角色
	 * 
	 * @return 用户角色列表
	 */
	@SuppressWarnings("unchecked")
	public List<UserRole> findAllUserRoles() {
		return (List<UserRole>) super.getHibernateTemplate().find(
				"FROM UserRole");
	}

	/**
	 * 通过用户角色ID查找用户角色
	 * 
	 * @param userRoleId
	 *            用户角色ID
	 * @return 用户角色
	 */
	public UserRole loadUserRole(Integer userRoleId) {
		return (UserRole) get(userRoleId);
	}

	/**
	 * 保存用户角色
	 * 
	 * @param userRole
	 *            待保存的用户角色
	 * @return 被保存的用户角色
	 */
	public UserRole saveUserRole(UserRole userRole) {
		Timestamp createAt = Timestamp.valueOf(new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
				.getTime()));
		userRole.setCreateAt(createAt);
		return (UserRole) save(userRole);
	}

	/**
	 * 更新用户角色
	 * 
	 * @param userRole
	 *            待更新的用户角色
	 * @return 被更新的用户角色
	 */
	public UserRole updateUserRole(UserRole userRole) {
		Timestamp updateAt = Timestamp.valueOf(new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
				.getTime()));
		userRole.setUpdateAt(updateAt);
		return (UserRole) update(userRole);
	}

	/**
	 * 删除用户角色
	 * 
	 * @param userRole
	 *            待删除的用户角色
	 */
	public void removeUserRole(UserRole userRole) {
		super.getHibernateTemplate().delete(userRole);
	}

	/**
	 * 通过角色ID查找对应的用户角色
	 * 
	 * @param roleId
	 *            角色ID
	 * @return 用户角色列表
	 */
	@SuppressWarnings("unchecked")
	public List<UserRole> findUserRolesByRoleId(Integer roleId) {
		List<UserRole> list = (List<UserRole>) super.getHibernateTemplate()
				.find("FROM UserRole ur WHERE ur.roleId=?", roleId);
		return list;
	}

	/**
	 * 通过用户ID查找该用户拥有的用户角色
	 * 
	 * @param userId
	 *            用户ID
	 * @return 用户角色列表
	 */
	@SuppressWarnings("unchecked")
	public List<UserRole> findUserRolesByUserId(Integer userId) {
		List<UserRole> list = (List<UserRole>) super.getHibernateTemplate()
				.find("FROM UserRole ur WHERE ur.userId=?", userId);
		return list;
	}

	/**
	 * 通过用户ID和角色ID查找用户角色
	 * 
	 * @param userId
	 *            用户ID
	 * @param roleId
	 *            角色ID
	 * @return 用户角色
	 */
	@SuppressWarnings("unchecked")
	public UserRole findUserRoleByIds(Integer userId, Integer roleId) {
		List<UserRole> list = (List<UserRole>) super.getHibernateTemplate()
				.find("FROM UserRole ur WHERE ur.userId=? and ur.roleId=?",
						userId, roleId);
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}