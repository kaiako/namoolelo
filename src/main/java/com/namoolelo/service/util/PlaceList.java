package com.namoolelo.service.util;

import java.util.ArrayList;
import java.util.List;

import com.namoolelo.domain.Place;

public class PlaceList {

	private List<Place> places = new ArrayList<Place>();
	
	public List<Place> getPlaces(){
		return places;
	}
	
	public void setPlaces(List<Place> places){
		this.places = places;
	}
}
