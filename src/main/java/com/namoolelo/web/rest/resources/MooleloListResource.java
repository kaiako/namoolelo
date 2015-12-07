package com.namoolelo.web.rest.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MooleloListResource extends ResourceSupport{
	
	private List<MooleloWithOutPlacesResource> moolelos = new ArrayList<MooleloWithOutPlacesResource>();

	public void setMoolelos(List<MooleloResource> list){
		list.parallelStream().forEach(res -> {
			MooleloWithOutPlacesResource mooleloRes = new MooleloWithOutPlacesResource();
			mooleloRes.setMoolelo(res.toMoolelo());
			moolelos.add(mooleloRes);			
		});
	}
}
