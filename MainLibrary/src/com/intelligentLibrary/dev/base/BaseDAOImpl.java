package com.intelligentLibrary.dev.base;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseDAOImpl<T> implements BaseDAO<T> {

	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public BaseDAOImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

	@Override
	public void save(T entity) {
		getSession().save(entity);

	}

	@Override
	public void delete(int id) {
		Object obj = getSession().get(clazz, id);
		getSession().delete(obj);
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);

	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(int id) {
		if (id == 0) {
			return null;
		}
		return (T)getSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getByIds(Integer[] ids) {
		if (ids == null || ids.length == 0) {
			// ÒÔ·À·µ»Ø¿Õ±¨´í
			return Collections.EMPTY_LIST;
		}

		Collection<Integer> cs = Arrays.asList(ids);
		return getSession().createQuery("From " + clazz.getSimpleName() + " where id in(:ids)")
				.setParameterList("ids", cs).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return getSession().createQuery("From " + clazz.getSimpleName()).list();
	}

	public void closeSession() {
		getSession().close();
	}

}
