package com.namoolelo.domain.locations.support;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javadocmd.simplelatlng.LatLng;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class GeoCoord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8648967158245870548L;
	
	private float latitude;
	private float longitude;
	@JsonIgnore
	@Transient
	private LatLng latLng;
	
	public GeoCoord(float lat, float lng){
		this.latitude = lat;
		this.longitude = lng;
	}
	
	public LatLng getLatLng(){
		if(latLng == null){
			latLng = new LatLng(latitude, longitude);
		}
		return latLng;
	}
	
}
