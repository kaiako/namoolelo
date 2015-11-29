package com.namoolelo.dao;

import java.io.Serializable;
import java.util.List;

import com.namoolelo.domain.Identifiable;

public interface GenericDao<T extends Serializable & Identifiable<I>, I extends Serializable> {

	/**
	 * Return a unique result, if more then one object is found only the first result is returned.
	 * @param id
	 * @return Returns a <code>T</code>.  
	 */
	public T find(I id);
		
	/**
	 * Delete given object
	 * @param obj
	 */
	public void delete(T obj);
	
	/**
	 * Save or update the Object, if an ID is not present on the object it is saved, otherwise updated.
	 * @param obj
	 */
	public void saveOrUpdate(T obj);
	
	public void save(T obj);
	
	public List<T> getAll();
}
