/***********************************************
 * Filename        : BaseEntity.java    
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.dao;

import com.innovaee.eorder.module.entity.BaseEntity;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;

/**
 * @Title: BaseDao
 * @Description: 所有DAO的基类
 *
 * @version V1.0
 */
@SuppressWarnings("unchecked")
public abstract class BaseDao extends HibernateDaoSupport {
	/**
	 * 获取实体
	 * 
	 * @param pk
	 *            主键
	 * @return 获得的实体
	 */
	public BaseEntity get(final Serializable pk) {
		return (BaseEntity) getHibernateTemplate().get(getEntityClass(), pk);
	}

	/**
	 * 删除实体
	 * 
	 * @param pk
	 *            主键
	 * @return 被删除的实体
	 */
	public BaseEntity remove(final Serializable pk) {
		BaseEntity baseEntity = (BaseEntity) getHibernateTemplate().load(
				getEntityClass(), pk);
		getHibernateTemplate().delete(baseEntity);

		return baseEntity;
	}

	/**
	 * 更新实体
	 * 
	 * @param entity
	 *            实体
	 * @return 更新后的实体
	 */
	public BaseEntity update(final BaseEntity entity) {
		getHibernateTemplate().update(entity);

		return entity;
	}

	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            实体
	 * @return 新增的实体
	 */
	public BaseEntity save(BaseEntity entity) {
		getHibernateTemplate().save(entity);

		return entity;
	}

	/**
	 * 返回实体的类对象
	 * 
	 * @return 实体的类对象
	 */
	@SuppressWarnings("rawtypes")
	protected abstract Class getEntityClass();
}