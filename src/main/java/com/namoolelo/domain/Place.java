package com.namoolelo.domain;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name="place")
public class Place implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1613847481353444504L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Embedded
	private Location location;
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
