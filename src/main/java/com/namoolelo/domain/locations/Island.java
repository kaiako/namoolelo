package com.namoolelo.domain.locations;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.namoolelo.domain.enums.CoordType;
import com.namoolelo.domain.enums.LocationType;
import com.namoolelo.domain.locations.support.GeoBounds;
import com.namoolelo.domain.locations.support.GeoCoord;

@Data
@NoArgsConstructor
@Entity(name="location_island")
public class Island implements Location{

	/**
	 * 
	 */
	private static final long serialVersionUID = 611291506102588798L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@AttributeOverrides({
		@AttributeOverride(name="northeast.latitude",
			column=@Column(name="ne_lat")),
		@AttributeOverride(name="northeast.longitude",
        	column=@Column(name="ne_lng")),
		@AttributeOverride(name="southwest.latitude",
        	column=@Column(name="sw_lat")),
		@AttributeOverride(name="southwest.longitude",
        	column=@Column(name="sw_lng"))
	})
	private GeoBounds bounds;
	
	public Island(String name, GeoCoord northeast, GeoCoord southwest){
		this.name = name;
		this.bounds = new GeoBounds(northeast, southwest);
	}
	
	public Island(String name, float neLat, float neLng, float swLat, float swLng){
		this.name = name;
		GeoCoord northeast = new GeoCoord(neLat, neLng);
		GeoCoord southwest = new GeoCoord(swLat, swLng);
		this.bounds = new GeoBounds(northeast, southwest);
	}
	
	@Override
	public LocationType getLocationType() {
		return LocationType.ISLAND;
	}

	@Override
	public CoordType getCoordType() {
		return CoordType.BOUNDS;
	}

	@Override
	public boolean contains(GeoCoord coord) {
		return bounds.contains(coord);
	}

}
