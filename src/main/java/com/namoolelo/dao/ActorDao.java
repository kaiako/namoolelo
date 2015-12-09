package com.namoolelo.dao;

import com.namoolelo.domain.Actor;

public interface ActorDao extends GenericDao<Actor, Long> {

	Actor findByName(String name);

}
