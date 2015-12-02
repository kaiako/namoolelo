package com.namoolelo.web.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.namoolelo.domain.Moolelo;
import com.namoolelo.web.rest.controllers.MooleloController;
import com.namoolelo.web.rest.resources.MooleloResource;

public class MooleloResourceAsm extends ResourceAssemblerSupport<Moolelo, MooleloResource>{

	public MooleloResourceAsm() {
		super(MooleloController.class, MooleloResource.class);
	}

	@Override
	public MooleloResource toResource(Moolelo moolelo) {
        MooleloResource res = new MooleloResource();
        res.setMoolelo(moolelo);
        res.add(linkTo(methodOn(MooleloController.class).getMoolelo(moolelo.getId())).withSelfRel());
        return res;
	}

}
