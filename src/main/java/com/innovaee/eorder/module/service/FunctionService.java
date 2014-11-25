package com.innovaee.eorder.module.service;

import java.util.List;

import javax.annotation.Resource;

import com.innovaee.eorder.module.dao.FunctionDao;
import com.innovaee.eorder.module.entity.Function;

public class FunctionService extends BaseService {

	@Resource
	private FunctionDao functionDao;

	@Resource
	private FunctionService functionService;

	public List<Function> findAllFunctions() {
		return (List<Function>) functionDao.findAllFunctions();
	}

	public List<Function> findFunctionsByFunctionName(String functionName) {
		return (List<Function>) functionDao.findFunctionsByFunctionName(functionName);
	}

}