package com.namoolelo.web.rest.resources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.namoolelo.domain.Place;

public class MooleloWithOutPlacesResource extends MooleloResource{
	
	@JsonProperty
	@Override
	public void setPlaces(List<Place> places) {
		super.setPlaces(places);
	}
	
	@JsonIgnore
	@Override
	public List<Place> getPlaces() {
		return super.getPlaces();
	}

}
