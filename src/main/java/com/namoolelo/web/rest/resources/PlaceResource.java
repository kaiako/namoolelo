package com.namoolelo.web.rest.resources;


import lombok.Getter;
import lombok.Setter;

import org.springframework.hateoas.ResourceSupport;

import com.namoolelo.domain.Location;
import com.namoolelo.domain.Moolelo;
import com.namoolelo.domain.Place;
import com.namoolelo.domain.enums.Island;
import com.namoolelo.domain.enums.Moku;

@Setter
@Getter
public class PlaceResource extends ResourceSupport{

	private Long rid;
	private String name;
	private Island island;
	private Moku moku;
	private Location location;
	private Moolelo moolelo;

	public void setPlaceResource(Place place){
		this.rid = place.getId();
		this.name = place.getName();
		this.island = place.getIsland();
		this.moku = place.getMoku();
		this.location = place.getLocation();
		this.moolelo = place.getMoolelo();		
	}
	
	public Place toPlace(){
		Place place = new Place();
		place.setId(rid);
		place.setName(name);
		place.setIsland(island);
		place.setMoku(moku);
		place.setLocation(location);
		place.setMoolelo(moolelo);
		return place;
	}

}
