package com.innovaee.eorder.module.service;

import java.util.List;

import javax.annotation.Resource;

import com.innovaee.eorder.module.dao.AclDao;
import com.innovaee.eorder.module.entity.Acl;

public class AclService extends BaseService {

	@Resource
	private AclDao aclDao;

	@Resource
	private AclService aclService;

	public List<Acl> findAllAcls() {
		return (List<Acl>) aclDao.findAllAcls();
	}

	public List<Acl> findAclsByAclName(String aclName) {
		return (List<Acl>) aclDao.findAcl(aclName);
	}

}