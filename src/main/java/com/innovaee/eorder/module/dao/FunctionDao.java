package com.innovaee.eorder.module.dao;

import java.util.List;

import com.innovaee.eorder.module.entity.Function;

public class FunctionDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return Function.class;
	}

	@SuppressWarnings("unchecked")
	public List<Function> findAllFunctions() {
		return (List<Function>) super.getHibernateTemplate().find("FROM Function");
	}

	@SuppressWarnings("unchecked")
	public List<Function> findFunctionsByFunctionName(String functionName) {
		return (List<Function>) super.getHibernateTemplate().find("FROM Function f WHERE f.functionName=?", functionName);
	}
}
