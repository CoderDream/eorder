/***********************************************
 * Filename       : BaseSpringTestCase.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.service;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Title: BaseSpringTestCase
 * @Description: 测试基类
 * @version V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext-security.xml",
		"classpath:applicationContext-total.xml", })
public class BaseSpringTestCase {

	/** 日志对象 */
	protected static final Logger LOGGER = Logger
			.getLogger(BaseSpringTestCase.class);

}