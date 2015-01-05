/***********************************************
 * Filename       : UserServiceTest.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.service;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.vo.UserVO;

/**
 * @Title: UserServiceTest
 * @Description: 用户服务测试类
 * @version V1.0
 */
@FixMethodOrder(MethodSorters.DEFAULT)
public class UserServiceTest extends BaseSpringTestCase {

	/** 用户服务类对象 */
	@Autowired
	private UserService userService;

	/** 用户名 */
	private String username = "abcba";

	/** 密码 */
	private String password = "12345";

	/** 手机号码 */
	private String cellphone = "13888888888";

	/** 等级ID */
	private Integer levelId = 1;

	/** 用户状态 */
	private Boolean userStatus = true;

	/**
	 * 测试获得所有用户值对象列表
	 */
	@Test
	public void getAllUsers() {
		List<UserVO> allUserVOs = userService.findAllUserVOs();
		Assert.assertNotNull(allUserVOs);
		for (UserVO uservo : allUserVOs) {
			LOGGER.debug(uservo);
		}
	}

	/**
	 * 先增加，再查找，再删除，再查找
	 */
	@Test
	public void operateUser() {
		// 先新增一个对象
		User user = new User(username, password, cellphone, levelId, userStatus);

		// 1. 保存
		User userNew = userService.saveUser(user);

		// 更新属性
		// 2. 更新
		String newCellphone = "13999999999";
		userNew.setCellphone(newCellphone);
		userService.updateUser(userNew);

		// 3. 查找
		// 3.1 通过用户ID查找
		User userDB = userService.findUserByUsernameAndPassword(username,
				password);
		Assert.assertNotNull(userDB);

		// 3.2 查找（通过手机号码查找用户）
		userDB = userService.findUserByCellphone(newCellphone);
		Assert.assertNotNull(userDB);
		Assert.assertEquals(newCellphone, userDB.getCellphone());

		// 3.3 通过用户名查找用户
		userDB = userService.findUserByUserName(userNew.getUsername());
		Assert.assertNotNull(userDB);

		// 4. 删除
		userService.removeUser(userDB.getUserId());

		// 5.通过用户名密码查找用户
		userDB = userService.loadUser(userNew.getUserId());
		Assert.assertNull(userDB);
	}

	/**
	 * 查找（通过手机号码查找用户）
	 */
	@Test
	public void findUserByCellphone() {
		// 3.2 查找（通过手机号码查找用户）
		User userDB = userService.findUserByCellphone(cellphone);
		Assert.assertNull(userDB);
	}

	/**
	 * 查找（通过手机号码查找用户）
	 */
	@Test
	public void findUserByUserName() {
		// 3.2 查找（通过用户名查找用户）
		User userDB = userService.findUserByUserName(username);
		Assert.assertNull(userDB);
	}

	/**
	 * 查找（通过手机号码查找用户）
	 */
	@Test
	public void findUserByUsernameAndPassword() {
		// 加密用户名密码
		// 3.2 查找（通过手机号码查找用户）
		User userDB = userService.findUserByUsernameAndPassword(username,
				password);
		Assert.assertNull(userDB);
	}

}