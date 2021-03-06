/***********************************************
 * Filename        : Role.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Title: Role
 * @Description: 角色实体
 *
 * @version V1.0
 */
@Entity
@Table(name = "t_role")
public class Role extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Override
	public Serializable getPK() {
		return roleId;
	}

	/** 角色ID */
	@Id
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer roleId;

	/** 角色名称 */
	@Column(name = "ROLE_NAME")
	private String roleName;

	/** 角色描述 */
	@Column(name = "ROLE_DESC")
	private String roleDesc;

	/** 角色状态 */
	@Column(name = "ROLE_STATUS")
	private Boolean roleStatus;

	/**
	 * 构造函数
	 */
	public Role() {
	}

	/**
	 * 构造函数
	 * 
	 * @param roleId
	 *            角色ID
	 */
	public Role(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * 构造函数
	 * 
	 * @param roleName
	 *            角色名称
	 * @param roleDesc
	 *            角色描述
	 * @param roleStatus
	 *            角色状态
	 */
	public Role(String roleName, String roleDesc, Boolean roleStatus) {
		super();
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.roleStatus = roleStatus;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Boolean getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(Boolean roleStatus) {
		this.roleStatus = roleStatus;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName
				+ ", roleDesc=" + roleDesc + ", roleStatus=" + roleStatus
				+ ", createAt=" + super.getCreateAt() + ", updateAt="
				+ super.getUpdateAt() + "]";
	}

}
