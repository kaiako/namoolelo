package com.namoolelo.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import lombok.Data;

@Data
public class Character implements Identifiable<Long>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3370037581952550275L;
	
	private Long id;
	private String name;
	private String descriptions;
	@ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="moolelo_character", 
	    joinColumns=@JoinColumn(name="moolelo_id"), 
	    inverseJoinColumns=@JoinColumn(name="character_id")) 
	private Set<Moolelo> moolelos;
}
