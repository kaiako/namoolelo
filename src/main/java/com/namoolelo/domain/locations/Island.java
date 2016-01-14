package com.namoolelo.domain.locations;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.javadocmd.simplelatlng.window.RectangularWindow;
import com.namoolelo.domain.enums.CoordType;
import com.namoolelo.domain.enums.LocationType;
import com.namoolelo.domain.locations.coords.GeoBounds;
import com.namoolelo.domain.locations.coords.GeoCoord;

@Data
@NoArgsConstructor
public class Island implements Location{

	/**
	 * 
	 */
	private static final long serialVersionUID = 611291506102588798L;
	
	private Long id;
	private String name;
	private GeoBounds bounds;
	private RectangularWindow window;
	
	public Island(String name, GeoCoord northeast, GeoCoord southwest){
		this.name = name;
		this.bounds = new GeoBounds(northeast, southwest);
		this.window = new RectangularWindow(northeast.getLatLng(), southwest.getLatLng());
	}
	
	public Island(String name, float neLat, float neLng, float swLat, float swLng){
		this.name = name;
		GeoCoord northeast = new GeoCoord(neLat, neLng);
		GeoCoord southwest = new GeoCoord(swLat, swLng);
		this.bounds = new GeoBounds(northeast, southwest);
		this.window = new RectangularWindow(northeast.getLatLng(), southwest.getLatLng());
	}
	
	@Override
	public LocationType getLocationType() {
		return LocationType.ISLAND;
	}

	@Override
	public boolean contains(GeoCoord coords) {
		return window.contains(coords.getLatLng());
	}

	@Override
	public CoordType getCoordType() {
		return CoordType.BOUNDS;
	}

}
