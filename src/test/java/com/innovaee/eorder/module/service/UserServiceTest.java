package com.innovaee.eorder.module.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.utils.Md5Util;
import com.innovaee.eorder.module.vo.UserVO;
import com.innovaee.eorder.test.BaseSpringTestCase;

public class UserServiceTest extends BaseSpringTestCase {

	@Autowired
	private UserService userService;

	private String username = "abc123";
	private String password = "abc123";
	private String cellphone = "13888888888";
	private Integer levelId = 1;
	private Boolean userStatus = true;
	private Timestamp createAt = Timestamp
			.valueOf(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
					.format(Calendar.getInstance().getTime()));

	@Test
	public void getAllUsers() {
		List<UserVO> allUserVOs = userService.findAllUserVOs();
		Assert.assertNotNull(allUserVOs);
		for (UserVO uservo : allUserVOs) {
			System.out.println(uservo);
		}
	}

	@Test
	public void loadUser() {
		Integer userId = 2;
		User user = userService.loadUser(userId);
		Assert.assertNotNull(user);
		Assert.assertEquals("test", user.getUsername());
		System.out.println(user);
	}

	@Test
	public void findUsersByUserName_01() {
		String username = "admin";
		User user = userService.findUserByUserName(username);
		Assert.assertNotNull(user);
	}

	@Test
	public void findUsersByUserName_02() {
		String username = "test00";
		User user = userService.findUserByUserName(username);
		Assert.assertNotNull(user);
	}

	@Test
	public void saveUser() {
		String md5Password = Md5Util.getMD5Str(password + "{" + username + "}");
		User user = new User(username, md5Password, cellphone, levelId,
				userStatus, createAt);
		User userNew = userService.saveUser(user);

		// 检查
		User userDB = userService.loadUser(userNew.getUserId());
		Assert.assertNotNull(userDB);
		Assert.assertEquals("测试", userDB.getUsername());
	}

	@Test
	public void updateUser() {
		// 先新增一个对象
		String md5Password = Md5Util.getMD5Str(password + "{" + username + "}");
		User user = new User(username, md5Password, cellphone, levelId,
				userStatus, createAt);
		User userNew = userService.saveUser(user);

		// 得到新增后的ID
		Integer userId = userNew.getUserId();

		// 更新属性
		String newCellphone = "13999999999";
		userNew.setCellphone(newCellphone);
		userService.updateUser(userNew);

		// 检查
		User userDB = userService.loadUser(userId);
		Assert.assertNotNull(userDB);
		Assert.assertEquals(newCellphone, userDB.getCellphone());
	}

	/**
	 * 01.先创建，后删除
	 */
	@Test
	public void removeUser_01() {
		String md5Password = Md5Util.getMD5Str(password + "{" + username + "}");
		User user = new User(username, md5Password, cellphone, levelId,
				userStatus, createAt);
		User userNew = userService.saveUser(user);
		Integer userId = userNew.getUserId();
		userService.removeUser(userId);
		// 检查
		User userDB = userService.loadUser(userId);
		Assert.assertNull(userDB);
	}

	/**
	 * 02.传入特定的userId后直接删除
	 */
	@Test
	public void removeUser_02() {
		Integer userId = 8;
		userService.removeUser(userId);
		// 检查
		User userDB = userService.loadUser(userId);
		Assert.assertNull(userDB);
	}

}