package com.namoolelo.web.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.namoolelo.domain.Moolelo;
import com.namoolelo.domain.Place;
import com.namoolelo.web.rest.controllers.MooleloController;
import com.namoolelo.web.rest.controllers.PlaceController;
import com.namoolelo.web.rest.resources.PlaceResource;

public class PlaceResourceAsm extends ResourceAssemblerSupport<Place, PlaceResource> {
	
	public PlaceResourceAsm() {
		super(PlaceController.class,PlaceResource.class);
	}

	@Override
	public PlaceResource toResource(Place place) {
		PlaceResource res = new PlaceResource();
		res.setPlaceResource(place);
        res.add(linkTo(methodOn(PlaceController.class).getPlace(place.getId())).withSelfRel());
        Moolelo moolelo = place.getMoolelo();
        if(moolelo != null)
        	res.add(linkTo(methodOn(MooleloController.class).getMoolelo(moolelo.getId())).withRel("parent"));
		return res;
	}

}
