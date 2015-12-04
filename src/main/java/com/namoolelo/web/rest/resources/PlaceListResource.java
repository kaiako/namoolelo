package com.namoolelo.web.rest.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class PlaceListResource extends ResourceSupport{
	
	
    private List<PlaceResource> places = new ArrayList<PlaceResource>();

    public List<PlaceResource> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlaceResource> places) {
        this.places = places;
    }

}
