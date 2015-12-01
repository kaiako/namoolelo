package com.namoolelo.web.rest.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MooleloListResource extends ResourceSupport{
	
	private List<MooleloResource> moolelos = new ArrayList<MooleloResource>();
}
