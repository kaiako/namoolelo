package com.namoolelo.dao;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.namoolelo.domain.Identifiable;

public abstract class GenericDaoImpl<T extends Serializable & Identifiable<I>, I extends Serializable> implements GenericDao<T, I>{
	
	private Class<T> type;
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    protected SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            throw new IllegalStateException("SessionFactory has not been set on DAO before usage");
        return sessionFactory;
    }

    public Class<T> getType() {
        return type;
    }
	
	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Transactional(readOnly = true)
	@Override
	public T find(I id) {
		Assert.notNull(id);
		return (T) getSessionFactory().getCurrentSession().get(getType(), id);
	}

	@Transactional
	@Override
	public void delete(T obj) {
		Assert.notNull(obj);
		getSessionFactory().getCurrentSession().delete(obj);
	}

	@Transactional
	@Override
	public void saveOrUpdate(T obj) {
		Assert.notNull(obj);
		getSessionFactory().getCurrentSession().saveOrUpdate(obj);
	}
	
	@Transactional
	@Override
	public void save(T obj) {
		Assert.notNull(obj);
		getSessionFactory().getCurrentSession().save(obj);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<T> getAll(){
		return (List<T>) getCriteria().list();
	}
	
	protected Criteria getCriteria() {
		return getSessionFactory().getCurrentSession().createCriteria(getType());
	}
	
}
