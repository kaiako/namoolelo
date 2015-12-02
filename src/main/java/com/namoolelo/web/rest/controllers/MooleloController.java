package com.namoolelo.web.rest.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.namoolelo.domain.Moolelo;
import com.namoolelo.service.MooleloService;
import com.namoolelo.service.util.MooleloList;
import com.namoolelo.web.rest.model.Envelope;
import com.namoolelo.web.rest.resources.MooleloListResource;
import com.namoolelo.web.rest.resources.asm.MooleloListResourceAsm;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@RestController
@RequestMapping("/rest/moolelos")
public class MooleloController {
	
	@Autowired
	private MooleloService mooleloService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<MooleloListResource> findAllMoolelos(@RequestParam(value="title", required = false) String title) {
        MooleloList list = null;
        if(title == null) {
            list = mooleloService.findAllMoolelos();
        } else {
            list = mooleloService.findMoolelosByTitle(title,true);
            if(list == null) {
                list = new MooleloList(new ArrayList<Moolelo>());
            } 
        }
        MooleloListResource res = new MooleloListResourceAsm().toResource(list);
        return new ResponseEntity<MooleloListResource>(res, HttpStatus.OK);
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
