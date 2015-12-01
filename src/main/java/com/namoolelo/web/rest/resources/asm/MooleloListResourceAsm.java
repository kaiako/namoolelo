package com.namoolelo.web.rest.resources.asm;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.namoolelo.service.util.MooleloList;
import com.namoolelo.web.rest.controllers.MooleloController;
import com.namoolelo.web.rest.resources.MooleloListResource;
import com.namoolelo.web.rest.resources.MooleloResource;

public class MooleloListResourceAsm extends ResourceAssemblerSupport<MooleloList, MooleloListResource> {

	public MooleloListResourceAsm() {
		super(MooleloController.class, MooleloListResource.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public MooleloListResource toResource(MooleloList list) {
		List<MooleloResource> resList = new MooleloResourceAsm().toResources(list.getMoolelos());
		MooleloListResource finalRes = new MooleloListResource();
		finalRes.setMoolelos(resList);
		return finalRes;
	}

}
