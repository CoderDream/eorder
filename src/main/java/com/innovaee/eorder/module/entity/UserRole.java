package com.innovaee.eorder.module.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ROLE")
public class UserRole extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Override
	public Serializable getPK() {
		return userRole;
	}

	@Id
	@Column(name = "user_role")
	private String userRole;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "role_name")
	private String roleName;

	public UserRole() {
	}

	public UserRole(String userRole) {
		this.userRole = userRole;
	}

	public UserRole(String userRole, String userName, String roleName) {
		super();
		this.userRole = userRole;
		this.userName = userName;
		this.roleName = roleName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "UserRole [userRole=" + userRole + ", userName=" + userName + ", roleName=" + roleName + "]";
	}

}