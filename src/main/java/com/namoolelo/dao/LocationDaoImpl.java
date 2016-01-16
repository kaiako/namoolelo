package com.namoolelo.dao;

import java.util.List;

import com.namoolelo.domain.locations.Island;
import com.namoolelo.domain.locations.Location;
import com.namoolelo.domain.locations.Moku;

public class LocationDaoImpl extends GenericDaoImpl<Location, Long> implements LocationDao {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Island> getAllIslands() {
		return sessionFactory.getCurrentSession().createCriteria(Island.class)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Moku> getAllMokus() {
		return sessionFactory.getCurrentSession().createCriteria(Moku.class)
				.list();
	}
	


}
