package com.namoolelo.web.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.namoolelo.domain.Moolelo;
import com.namoolelo.service.MooleloService;
import com.namoolelo.web.rest.model.Envelope;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@RestController
@RequestMapping("/rest/moolelos")
public class MooleloController {
	
	@Autowired
	private MooleloService mooleloService;

	@RequestMapping(method=RequestMethod.POST)
	public Envelope create(@RequestBody Moolelo story){
		log.info("Creating Story " + story.getTitle());
		mooleloService.saveOrUpdate(story);
		return new Envelope(story.getId());
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Envelope getAll(){
		log.info("Get all Stories");
		return new Envelope(mooleloService.getAllMoolelos());
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Envelope getStory(@PathVariable long id){
		log.info("Get Story with id : "+id);
		return new Envelope(mooleloService.getMoolelo(id));
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public Envelope updateStory(@PathVariable long id, @RequestBody Moolelo story){
		log.info("Save Story with id : "+id);
		if(id != story.getId()){
			return new Envelope(HttpStatus.FORBIDDEN, "Rest path is not the same as story id");
		}
		mooleloService.saveOrUpdate(story);
		return new Envelope(id);
	}
}
