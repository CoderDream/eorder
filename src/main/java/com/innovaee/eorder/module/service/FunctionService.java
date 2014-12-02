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

	public Function findFunctionsByFunctionName(String functionName) {
		return (Function) functionDao.findFunctionsByFunctionName(functionName);
	}

	public Function loadFunction(Integer functionId) {
		Function function = (Function) functionDao.get(functionId);

		if (null == function) {
			return null;
		}

		return function;
	}

	public Function saveFunction(Function function) {
		return functionDao.saveFunction(function);
	}

	public void updateFunction(Function function) {
		functionDao.updateFunction(function);
	}

	public void removeFunction(Integer functionId) {
		functionDao.removeFunction(new Function(functionId));
	}

	public void removeFunctions(String[] functionIds) {
		int length = functionIds.length;
		for (int i = 0; i < length; i++) {
			functionDao.removeFunction(new Function(Integer
					.parseInt(functionIds[i])));
		}
	}

}