/***********************************************
 * Filename        : UserOpAction.java    
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.web.action.admin.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.service.UserRoleService;
import com.innovaee.eorder.module.service.UserService;
import com.innovaee.eorder.module.utils.Constants;
import com.innovaee.eorder.module.utils.Md5Util;
import com.innovaee.eorder.module.utils.MenuUtil;
import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.module.vo.UserDetailsVo;
import com.innovaee.eorder.module.vo.UserVO;
import com.innovaee.eorder.web.action.BaseAction;

/**
 * @Title: UserOpAction
 * @Description: 用户操作Action（新增和修改）
 *
 * @version V1.0
 */
public class UserOpAction extends BaseAction {

	/** 用户ID */
	private String userId;

	/** 用户名称 */
	private String username;

	/** 密码 */
	private String password;

	/** 电话号码 */
	private String cellphone;

	/** 用户值对象列表 */
	private List<UserVO> uservos = new ArrayList<UserVO>();

	/** 用户服务类对象 */
	@Resource
	private UserService userService;

	/** 用户角色服务类对象 */
	@Resource
	private UserRoleService userRoleService;

	/** 已有的角色列表 */
	private List<Role> myRoles = new ArrayList<Role>();

	/** 剩余的角色列表 */
	private List<Role> leftRoles = new ArrayList<Role>();

	/** 已有的角色数组 */
	private String myRolesArray;

	/** 剩余的角色数组 */
	private String leftRolesArray;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	public void validate() {
		refreshData();
	}

	/**
	 * 保存前校验
	 */
	public void validateSave() {
		// 查看用户名是否已存在
		User user = userService.findUserByUserName(username);
		if (null != user) {
			addFieldError("username", "用户名已被占用！");
			// 更新页面数据
			refreshData();
		}

		// 查看手机号码是否已存在
		user = userService.findUserByCellphone(cellphone);
		if (null != user) {
			addFieldError("cellphone", "手机号码已被占用！");
			// 更新页面数据
			refreshData();
		}
	}

	/**
	 * 更新前校验
	 */
	public void validateUpdate() {
		// 查看用户名是否已存在
		User user1 = userService.loadUser(Integer.parseInt(userId));
		User user2 = userService.findUserByUserName(username);
		// 可以找到，而且和自己的名字不同，则说明已经被占用
		if (null != user2
				&& (user1.getUserId().intValue() != user2.getUserId()
						.intValue())) {
			addFieldError("username", "用户名已被占用！");
			// 更新页面数据
			refreshData();
		}

		// 查看手机号码是否已存在
		// 可以找到，而且和自己的手机不同，则说明已经被占用
		User user3 = userService.findUserByCellphone(cellphone);
		if (null != user3 && !cellphone.equals(user1.getCellphone())) {
			addFieldError("cellphone", "手机号码已被占用！");
			// 更新页面数据
			refreshData();
		}
	}

	/**
	 * 清空输入框数据
	 */
	private void clean() {
		this.setUserId("");
		this.setUsername("");
		this.setPassword("");
		this.setCellphone("");
	}

	/**
	 * 增加一个save方法，对应一个处理逻辑
	 * 
	 * @return
	 */
	public String save() {
		User user = new User();
		if (null != username && !"".equals(username.trim())) {
			user.setUsername(username);
		} else {
			addFieldError("username", "用户名不能为空！");

			// 更新页面数据
			refreshData();
			return INPUT;
		}

		if (null != password && !"".equals(password.trim())) {
			user.setPassword(password);
		} else {
			addFieldError("password", "密码不能为空！");
			// 更新页面数据
			refreshData();
			return INPUT;
		}
		if (null != cellphone && !"".equals(cellphone.trim())) {
			user.setCellphone(cellphone);
		} else {
			addFieldError("cellphone", "手机号码不能为空！");
			// 更新页面数据
			refreshData();
			return INPUT;
		}

		user.setLevelId(Constants.DEFAULT_LEVEL);
		user.setUserStatus(true);
		userService.saveUser(user);

		// 默认给用户添加普通用户的角色
		userRoleService.saveUserRole(user, new Role(Constants.DEFAULT_ROLE));

		this.setMessage("新增成功！");

		// 清空输入框数据
		clean();

		// 更新页面数据
		refreshData();

		return SUCCESS;
	}

	/**
	 * 更新
	 * 
	 * @return
	 */
	public String update() {
		User user = null;
		if (null != userId) {
			user = userService.loadUser(Integer.parseInt(userId));
		}

		if (null != username && !"".equals(username.trim())) {
			user.setUsername(username);
		} else {
			addFieldError("username", "用户名不能为空！");

			// 更新页面数据
			refreshData();
			return INPUT;
		}

		if (null != password && !"".equals(password.trim())) {
			// 由于数据库存储的是MD5加密后的密码，所以这里要处理一下
			// 首先判断是否修改过密码，及比对一下前台传过来的密码是否和数据库中的一致，
			// 如果相同，则直接赋值后更新；
			if (password.equals(user.getPassword())) {
				user.setPassword(password);
			} else { // 如果不相同，则说明修改了密码，则需要加密后再存储
				String md5Password = Md5Util.getMD5Str(password + "{"
						+ username + "}");
				user.setPassword(md5Password);
			}
		} else {
			addFieldError("password", "密码不能为空！");
			// 更新页面数据
			refreshData();
			return INPUT;
		}

		if (null != cellphone && !"".equals(cellphone.trim())) {
			user.setCellphone(cellphone);
		} else {
			addFieldError("cellphone", "手机号码不能为空！");
			// 更新页面数据
			refreshData();
			return INPUT;
		}
		userService.updateUser(user);

		// 更新角色信息
		userRoleService.updateUserRole(Integer.parseInt(userId), myRolesArray);

		this.setMessage("修改成功");
		// 清空输入框数据
		clean();

		// 更新页面数据
		refreshData();
		return SUCCESS;
	}

	/**
	 * 刷新页面数据
	 */
	@SuppressWarnings("unchecked")
	public void refreshData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		request.setAttribute("menulist", MenuUtil.getRoleLinkVOList());
		List<RoleLinkVo> sessionMenulist = (List<RoleLinkVo>) session
				.getAttribute("menulist");
		this.setMenulist(sessionMenulist);
		uservos = userService.findAllUserVOs();
		UserDetailsVo userDetail = (UserDetailsVo) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		this.setLoginName(userDetail.getUser().getUsername());
	}

	public List<UserVO> getUservos() {
		return uservos;
	}

	public void setUservos(List<UserVO> uservos) {
		this.uservos = uservos;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public List<Role> getMyRoles() {
		return myRoles;
	}

	public void setMyRoles(List<Role> myRoles) {
		this.myRoles = myRoles;
	}

	public List<Role> getLeftRoles() {
		return leftRoles;
	}

	public void setLeftRoles(List<Role> leftRoles) {
		this.leftRoles = leftRoles;
	}

	public String getMyRolesArray() {
		return myRolesArray;
	}

	public void setMyRolesArray(String myRolesArray) {
		this.myRolesArray = myRolesArray;
	}

	public String getLeftRolesArray() {
		return leftRolesArray;
	}

	public void setLeftRolesArray(String leftRolesArray) {
		this.leftRolesArray = leftRolesArray;
	}

}