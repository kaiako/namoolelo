package com.namoolelo.web.rest.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.namoolelo.domain.Place;
import com.namoolelo.service.PlaceService;
import com.namoolelo.service.util.PlaceList;
import com.namoolelo.web.rest.resources.PlaceListResource;
import com.namoolelo.web.rest.resources.PlaceResource;
import com.namoolelo.web.rest.resources.asm.PlaceListResourceAsm;
import com.namoolelo.web.rest.resources.asm.PlaceResourceAsm;

@Slf4j
@RestController
@RequestMapping("/rest/places")
public class PlaceController {
	
	@Autowired
	private PlaceService placeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<PlaceListResource> findAllPlaces(){
		log.info("Find all places");
		PlaceList list = placeService.findAllPlaces();
		PlaceListResource res = new PlaceListResourceAsm().toResource(list);
		return new ResponseEntity<PlaceListResource>(res, HttpStatus.OK);
	}	
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<PlaceResource> findPlace(@PathVariable("id") Long placeId) {
		log.info("Getting place for id : "+placeId);
        Place place = placeService.findPlace(placeId);
        if(place != null)
        {
            PlaceResource res = new PlaceResourceAsm().toResource(place);
            return new ResponseEntity<PlaceResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<PlaceResource>(HttpStatus.NOT_FOUND);
        }
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<PlaceResource> updatePlace(@PathVariable("id") Long placeId, @RequestBody Place place) {
		log.info("Getting place for id : "+placeId);
		if(place == null || placeId != place.getId()){
            return new ResponseEntity<PlaceResource>(HttpStatus.CONFLICT);
		}
		place = placeService.updatePlace(place);
        PlaceResource res = new PlaceResourceAsm().toResource(place);
        return new ResponseEntity<PlaceResource>(res, HttpStatus.OK);
	}
	
	

}
