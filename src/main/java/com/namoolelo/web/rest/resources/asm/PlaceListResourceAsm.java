package com.namoolelo.web.rest.resources.asm;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.namoolelo.service.util.PlaceList;
import com.namoolelo.web.rest.controllers.PlaceController;
import com.namoolelo.web.rest.resources.PlaceListResource;
import com.namoolelo.web.rest.resources.PlaceResource;

public class PlaceListResourceAsm extends ResourceAssemblerSupport<PlaceList, PlaceListResource>{

	public PlaceListResourceAsm() {
		super(PlaceController.class, PlaceListResource.class);
	}
	@Override
	public PlaceListResource toResource(PlaceList list) {
        List<PlaceResource> resList = new PlaceResourceAsm().toResources(list.getPlaces());
        PlaceListResource finalRes = new PlaceListResource();
        finalRes.setPlaces(resList);
        return finalRes;
	}

}
