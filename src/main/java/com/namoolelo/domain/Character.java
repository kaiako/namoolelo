package com.namoolelo.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="characters")
public class Character implements Identifiable<Long>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3370037581952550275L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String name;
	private String description;
	@ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="moolelo_character", 
	    joinColumns=@JoinColumn(name="moolelo_id"), 
	    inverseJoinColumns=@JoinColumn(name="character_id")) 
	private Set<Moolelo> moolelos;
}
