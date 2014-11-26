package com.innovaee.eorder.module.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACL")
public class Acl extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Override
	public Serializable getPK() {
		return roleFunction;
	}

	@Id
	@Column(name = "role_function")
	private String roleFunction;
	@Column(name = "role_name")
	private String roleName;
	@Column(name = "function_name")
	private String functionName;

	public Acl() {
	}

	public Acl(String roleFunction) {
		this.roleFunction = roleFunction;
	}

	public Acl(String roleFunction, String roleName, String functionName) {
		super();
		this.roleFunction = roleFunction;
		this.roleName = roleName;
		this.functionName = functionName;
	}

	public String getRoleFunction() {
		return roleFunction;
	}

	public void setRoleFunction(String roleFunction) {
		this.roleFunction = roleFunction;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	@Override
	public String toString() {
		return "Acl [roleFunction=" + roleFunction + ", roleName=" + roleName + ", functionName=" + functionName + "]";
	}

}
