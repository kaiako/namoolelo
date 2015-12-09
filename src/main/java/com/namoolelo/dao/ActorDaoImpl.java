package com.namoolelo.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.namoolelo.domain.Actor;

@Transactional
@Repository
public class ActorDaoImpl extends GenericDaoImpl<Actor, Long> implements ActorDao{

	@Override
	public Actor findByName(String name) {
		return (Actor) getCriteria().add(Restrictions.eq("name", name)).uniqueResult();
	}

}
