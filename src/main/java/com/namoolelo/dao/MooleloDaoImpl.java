package com.namoolelo.dao;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.namoolelo.domain.Moolelo;

@Repository
public class MooleloDaoImpl extends GenericDaoImpl<Moolelo, Long> implements MooleloDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public Moolelo find(Long id) {
		Moolelo story = super.find(id);
		Hibernate.initialize(story.getPlaces());
		return story;
	}
}
