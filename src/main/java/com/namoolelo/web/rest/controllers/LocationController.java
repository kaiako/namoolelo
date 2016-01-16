package com.namoolelo.web.rest.controllers;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.namoolelo.domain.locations.Island;
import com.namoolelo.domain.locations.Moku;
import com.namoolelo.service.LocationService;

@Slf4j
@RestController
@RequestMapping("/rest/locations")
public class LocationController {
	
	@Autowired
	private LocationService locationService;
	
	@RequestMapping(value="/islands", method=RequestMethod.GET)
	public ResponseEntity<List<Island>> getIslands(){
		log.info("Get all islands");
		return new ResponseEntity<List<Island>>(locationService.getAllIslands(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/islands", method=RequestMethod.GET)
	public ResponseEntity<List<Moku>> getMokus(){
		log.info("Get all Moku");
		return new ResponseEntity<List<Moku>>(locationService.getAllMokus(), HttpStatus.OK);
	}

}
