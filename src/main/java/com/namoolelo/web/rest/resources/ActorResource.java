package com.namoolelo.web.rest.resources;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.springframework.hateoas.ResourceSupport;

import com.namoolelo.domain.Actor;
import com.namoolelo.domain.Moolelo;

@Setter
@Getter
public class ActorResource extends ResourceSupport {

	private Long rid;
	private String name;
	private String description;
	private Set<Moolelo> moolelos;
	
	public void setActor(Actor actor){
		this.rid = actor.getId();
		this.name = actor.getName();
		this.description = actor.getDescription();
		this.moolelos = actor.getMoolelos();
	}
	
	public Actor toActor(){
		Actor actor = new Actor();
		actor.setId(rid);
		actor.setName(name);
		actor.setDescription(description);
		actor.setMoolelos(moolelos);
		return actor;
	}
}
