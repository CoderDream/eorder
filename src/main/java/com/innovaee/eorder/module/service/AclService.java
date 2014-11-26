package com.innovaee.eorder.module.service;

import java.util.List;

import javax.annotation.Resource;

import com.innovaee.eorder.module.dao.AclDao;
import com.innovaee.eorder.module.dao.FunctionDao;
import com.innovaee.eorder.module.dao.RoleDao;
import com.innovaee.eorder.module.entity.Acl;
import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.entity.Role;

public class AclService extends BaseService {

	@Resource
	private AclDao aclDao;

	@Resource
	private RoleDao roleDao;

	@Resource
	private FunctionDao functionDao;

	@Resource
	private AclService aclService;

	public List<Acl> findAllAcls() {
		return (List<Acl>) aclDao.findAllAcls();
	}

	public List<Acl> findAclsByRoleName(String roleName) {
		return (List<Acl>) aclDao.findAclsByRoleName(roleName);
	}

	public Acl findAclByRoleFunction(String roleFunction) {
		return (Acl) aclDao.findAclByRoleFunction(roleFunction);
	}

	public void saveAcl(Acl acl) {
		aclDao.saveAcl(acl);
	}

	public Acl saveAcl(Role role, Function function) {
		Acl rtnAcl = null;
		Role roleDB = (Role) roleDao.get(role.getRoleName());
		Function functionDB = (Function) functionDao.get(function.getFunctionName());
		Acl acl = null;
		if (null != roleDB && null != functionDB) {
			String roleFunction = roleDB.getRoleName() + functionDB.getFunctionName();
			String roleName = roleDB.getRoleName();
			String functionName = functionDB.getFunctionName();
			acl = new Acl(roleFunction, roleName, functionName);
			Acl aclDB = (Acl) aclDao.get(roleFunction);
			// 如果DB不存在，就添加
			if (null == aclDB) {
				rtnAcl = aclDao.saveAcl(acl);
			}
		}

		return rtnAcl;

	}

	public void removeAcl(Role role, Function function) {
		Role roleDB = (Role) roleDao.get(role.getRoleName());
		Function functionDB = (Function) functionDao.get(function.getFunctionName());
		// Acl acl = null;
		if (null != roleDB && null != functionDB) {
			String roleFunction = roleDB.getRoleName() + functionDB.getFunctionName();
			//String roleName = roleDB.getRoleName();
			//String functionName = functionDB.getFunctionName();
			// acl = new Acl(roleFunction, roleName, functionName);
			Acl aclDB = (Acl) aclDao.get(roleFunction);
			// 如果DB不存在，就添加
			if (null != aclDB) {
				aclDao.removeAcl(aclDB);
			}
		}

	}

	public void updateAcl(Acl acl) {
		aclDao.updateAcl(acl);
	}

	public void removeAcl(String roleFunction) {
		aclDao.removeAcl(new Acl(roleFunction));
	}

	public void removeAcls(String[] roleFunctions) {
		int length = roleFunctions.length;
		for (int i = 0; i < length; i++) {
			aclDao.removeAcl(new Acl(roleFunctions[i]));
		}

	}

}