package com.namoolelo.web.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.namoolelo.domain.Actor;
import com.namoolelo.domain.Moolelo;
import com.namoolelo.web.rest.controllers.ActorController;
import com.namoolelo.web.rest.controllers.MooleloController;
import com.namoolelo.web.rest.resources.ActorResource;

public class ActorResourceAsm extends ResourceAssemblerSupport<Actor, ActorResource> {

	public ActorResourceAsm() {
		super(ActorController.class, ActorResource.class);
	}
	@Override
	public ActorResource toResource(Actor actor) {
		ActorResource res = new ActorResource();
		res.setActor(actor);
        res.add(linkTo(methodOn(ActorController.class).getActor(actor.getId())).withSelfRel());	
        for(Moolelo moolelo : actor.getMoolelos()){
        	res.add(linkTo(methodOn(MooleloController.class).getMoolelo(moolelo.getId())).withRel("related"));		
        }
		return res;
	}

}
