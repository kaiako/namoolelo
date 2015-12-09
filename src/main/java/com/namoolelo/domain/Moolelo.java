package com.namoolelo.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Data;

@Data
@Entity
@Table(name="moolelo")
public class Moolelo implements Identifiable<Long>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -171917254416878053L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private Account owner;
	private String title;
	private String text;
	private String summary;
	@Column(name="est_date")
	private String estDate;
	@OneToMany(mappedBy="moolelo")
	@Cascade(CascadeType.ALL)
	private List<Place> places;
	@ManyToMany(mappedBy="moolelos")
	private Set<Actor> characters;
	
}
