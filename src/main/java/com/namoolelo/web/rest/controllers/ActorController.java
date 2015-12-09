package com.namoolelo.web.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.namoolelo.domain.Actor;
import com.namoolelo.exceptions.web.NotFoundException;
import com.namoolelo.service.ActorService;
import com.namoolelo.web.rest.resources.ActorResource;
import com.namoolelo.web.rest.resources.asm.ActorResourceAsm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest/actors")
public class ActorController {

	@Autowired
	private ActorService actorService;
	
	@RequestMapping(value="/{actorId}", method=RequestMethod.GET)
	public ResponseEntity<ActorResource> getActor(@PathVariable long actorId) {
		log.info("Getting Actor with id : "+actorId);
		try{
			Actor actor = actorService.findActor(actorId);
        	ActorResource res = new ActorResourceAsm().toResource(actor);
            return new ResponseEntity<ActorResource>(res, HttpStatus.OK);
        } catch(NotFoundException e) {
        	log.info("Actor NOT FOUND for id : "+actorId);
            return new ResponseEntity<ActorResource>(HttpStatus.NOT_FOUND);
        }   
		
	}

}
