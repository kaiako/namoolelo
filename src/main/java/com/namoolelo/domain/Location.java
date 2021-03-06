package com.namoolelo.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class Location implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8648967158245870548L;
	
	private float latitude;
	private float longitude;
	
	public Location(float lat, float lng){
		this.latitude = lat;
		this.longitude = lng;
	}
	
}
