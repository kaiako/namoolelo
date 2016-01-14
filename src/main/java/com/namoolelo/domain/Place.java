package com.namoolelo.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.namoolelo.domain.locations.Island;
import com.namoolelo.domain.locations.Location;
import com.namoolelo.domain.locations.Moku;

import lombok.Data;

@Data
@Entity
@Table(name="place")
public class Place implements Identifiable<Long>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1613847481353444504L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToOne(cascade=CascadeType.ALL,targetEntity=Island.class)
	private Location island;
	@OneToOne(cascade=CascadeType.ALL,targetEntity=Moku.class)
	private Location moku;
	@ManyToOne
	private Moolelo moolelo;
	
	@JsonIgnore
	public Moolelo getMoolelo(){
		return moolelo;
	}
	@JsonProperty
	public void setMoolelo(Moolelo moolelo){
		this.moolelo = moolelo;
	}
}
