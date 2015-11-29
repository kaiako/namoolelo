package com.namoolelo.dao;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.namoolelo.domain.Story;

@Repository
public class StoryDaoImpl extends GenericDaoImpl<Story, Long> implements StoryDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public Story find(Long id) {
		Story story = super.find(id);
		Hibernate.initialize(story.getPlaces());
		return story;
	}
}
