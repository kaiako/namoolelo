package com.namoolelo.domain.locations.support;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.javadocmd.simplelatlng.window.RectangularWindow;

import lombok.Data;

@Data
@Embeddable
public class GeoBounds {
	private GeoCoord northeast;
	private GeoCoord southwest;
	@Transient
	private RectangularWindow window;

	public GeoBounds(GeoCoord northeast, GeoCoord southwest) {
		this.northeast = northeast;
		this.southwest = southwest;
		this.window = new RectangularWindow(northeast.getLatLng(), southwest.getLatLng());
	}

	public boolean contains(GeoCoord coord) {
		if(window == null){
			this.window = new RectangularWindow(northeast.getLatLng(), southwest.getLatLng());			
		}
		return window.contains(coord.getLatLng());
	}
	
	
	
}
