package com.innovaee.eorder.module.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.innovaee.eorder.module.entity.Acl;
import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.test.BaseSpringTestCase;

public class AclServiceTest extends BaseSpringTestCase {

	@Autowired
	private AclService aclService;

	@Test
	public void getAllAcls() {
		List<Acl> allAcls = aclService.findAllAcls();
		Assert.assertNotNull(allAcls);
		for (Acl acl : allAcls) {
			System.out.println(acl);
		}
	}

	@Test
	public void findAclByRoleFunction() {
		String roleFunction = "ROLE_admindoAdministration";
		Acl acl = aclService.findAclByRoleFunction(roleFunction);
		Assert.assertNotNull(acl);
		Assert.assertEquals("ROLE_admin", acl.getRoleName());
	}

	@Test
	public void findAclByRoleName() {
		String roleName = "ROLE_admin";
		List<Acl> acls = aclService.findAclsByRoleName(roleName);
		Assert.assertNotNull(acls);
		for (Acl acl : acls) {
			Assert.assertEquals("ROLE_admin", acl.getRoleName());
		}
	}

	@Test
	public void saveAcl() {
		String roleName = "ROLE_normal";
		String functionName = "doUser";
		Role role = new Role(roleName);
		Function function = new Function(functionName);
		Acl acl = aclService.saveAcl(role, function);
		Assert.assertNotNull(acl);
		Assert.assertEquals(roleName + functionName, acl.getRoleFunction());
	}

	@Test
	public void removeAcl() {
		String roleName = "ROLE_normal";
		String functionName = "doUser";
		Role role = new Role(roleName);
		Function function = new Function(functionName);
		aclService.removeAcl(role, function);
		String roleFunction = roleName + functionName;
		Acl acl = aclService.findAclByRoleFunction(roleFunction);
		Assert.assertNull(acl);
	}

}
