package com.namoolelo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namoolelo.dao.ActorDao;
import com.namoolelo.domain.Actor;
import com.namoolelo.domain.Moolelo;
import com.namoolelo.exceptions.ActorExistException;
import com.namoolelo.exceptions.IncorrectOwnerException;
import com.namoolelo.exceptions.web.NotFoundException;
import com.namoolelo.security.SecurityUtils;

@Service
public class ActorServiceImpl implements ActorService{

	@Autowired
	private MooleloService mooleloService;
	
	@Autowired
	private ActorDao characterDao;
	
	@Override
	public Actor createActor(long mooleloId, Actor actor) 
			throws IncorrectOwnerException {

		Moolelo moolelo = mooleloService.getMoolelo(mooleloId);
		if(moolelo.getOwner().getId() != SecurityUtils.getAccountId()){
			throw new IncorrectOwnerException();
		}
		if(characterDao.findByName(actor.getName()) != null){
			throw new ActorExistException();
		}
		actor.getMoolelos().add(moolelo);
		characterDao.saveOrUpdate(actor);
		return actor;
	}

	@Override
	public Actor findActor(long actorId) {
		Actor actor = characterDao.find(actorId);
		if(actor == null)
			throw new NotFoundException();
		return actor;
	}

}
