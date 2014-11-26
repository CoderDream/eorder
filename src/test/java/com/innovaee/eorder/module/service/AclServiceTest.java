package com.innovaee.eorder.module.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.innovaee.eorder.module.entity.Acl;
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
	public void findAclsByAclName() {
		String aclName = "ROLE_admin";
		List<Acl> allAcls = aclService.findAclsByAclName(aclName);
		Assert.assertNotNull(allAcls);
		for (Acl acl : allAcls) {
			System.out.println(acl);
		}
	}

}
