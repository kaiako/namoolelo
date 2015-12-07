package com.namoolelo.web.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.namoolelo.domain.Actor;
import com.namoolelo.web.rest.controllers.CharacterController;
import com.namoolelo.web.rest.resources.ActorResource;

public class ActorResourceAsm extends ResourceAssemblerSupport<Actor, ActorResource> {

	public ActorResourceAsm() {
		super(CharacterController.class, ActorResource.class);
	}
	@Override
	public ActorResource toResource(Actor actor) {
		ActorResource res = new ActorResource();
		res.setActor(actor);
        res.add(linkTo(methodOn(CharacterController.class).getActor(actor.getId())).withSelfRel());		
		return null;
	}

}
