package com.namoolelo.web.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.namoolelo.domain.Story;
import com.namoolelo.service.StoryService;
import com.namoolelo.web.rest.model.Envelope;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@RestController
@RequestMapping("/rest/story")
public class StoryController {
	
	@Autowired
	private StoryService storyService;

	@RequestMapping(value="/create",method=RequestMethod.POST)
	public Envelope create(@RequestBody Story story){
		log.info("Creating Story " + story.getTitle());
		storyService.saveOrUpdate(story);
		return new Envelope(story.getId());
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Envelope getAll(){
		log.info("Get all Stories");
		return new Envelope(storyService.getAllStories());
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Envelope getStory(@PathVariable long id){
		log.info("Get Story with id : "+id);
		return new Envelope(storyService.getStory(id));
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public Envelope updateStory(@PathVariable long id, @RequestBody Story story){
		log.info("Save Story with id : "+id);
		if(id != story.getId()){
			return new Envelope(HttpStatus.FORBIDDEN, "Rest path is not the same as story id");
		}
		storyService.saveOrUpdate(story);
		return new Envelope(id);
	}
}
