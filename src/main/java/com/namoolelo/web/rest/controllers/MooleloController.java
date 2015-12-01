package com.namoolelo.web.rest.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.namoolelo.domain.Moolelo;
import com.namoolelo.exceptions.AccountExistsException;
import com.namoolelo.exceptions.ConflictException;
import com.namoolelo.service.MooleloService;
import com.namoolelo.web.rest.model.Envelope;
import com.namoolelo.web.rest.resources.MooleloResource;
import com.namoolelo.web.rest.resources.asm.MooleloResourceAsm;

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
	public ResponseEntity<MooleloResource> create(@RequestBody MooleloResource moolelo){
		log.info("Creating Moolelo " + moolelo.getTitle());
        try {
            Moolelo createdMoolelo = mooleloService.createMoolelo(moolelo.toMoolelo());
            MooleloResource res = new MooleloResourceAsm().toResource(createdMoolelo);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<MooleloResource>(res, headers, HttpStatus.CREATED);
        } catch(AccountExistsException exception) {
            throw new ConflictException(exception);
        }
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Envelope findAllMoolelos(){
		log.info("find all Moolelos");
		return new Envelope(mooleloService.findAllMoolelos());
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Envelope getMoolelo(@PathVariable long id){
		log.info("Get Moolelo with id : "+id);
		return new Envelope(mooleloService.getMoolelo(id));
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public Envelope updateMoolelo(@PathVariable long id, @RequestBody Moolelo moolelo){
		log.info("Save Moolelo with id : "+id);
		if(id != moolelo.getId()){
			return new Envelope(HttpStatus.FORBIDDEN, "Rest path is not the same as story id");
		}
		mooleloService.saveOrUpdate(moolelo);
		return new Envelope(id);
	}
}
