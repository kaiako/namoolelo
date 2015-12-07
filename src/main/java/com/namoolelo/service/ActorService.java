package com.namoolelo.service;

import com.namoolelo.domain.Actor;
import com.namoolelo.exceptions.IncorrectOwnerException;
import com.namoolelo.exceptions.web.NotFoundException;

public interface ActorService {

	public Actor createActor(long mooleloId, Actor actor) throws IncorrectOwnerException;

	public Actor findActor(long actorId) throws NotFoundException;

}
