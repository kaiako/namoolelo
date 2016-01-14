package com.namoolelo.domain.locations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.namoolelo.domain.enums.CoordType;
import com.namoolelo.domain.enums.LocationType;
import com.namoolelo.domain.locations.support.GeoCoord;
import com.namoolelo.domain.locations.support.Polygon;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name="location_moku")
public class Moku implements Location {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6301761305401716122L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Polygon area;
	
	@Override
	public LocationType getLocationType() {
		return LocationType.MOKU;
	}

	@Override
	public boolean contains(GeoCoord coord) {
		return area.contains(coord);
	}

	@Override
	public CoordType getCoordType() {
		return CoordType.POLYGON;
	}

}
