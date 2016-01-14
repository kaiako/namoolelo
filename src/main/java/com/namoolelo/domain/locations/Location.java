package com.namoolelo.domain.locations;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import com.namoolelo.domain.Identifiable;
import com.namoolelo.domain.enums.CoordType;
import com.namoolelo.domain.enums.LocationType;
import com.namoolelo.domain.locations.support.GeoCoord;

@MappedSuperclass
public interface Location extends Identifiable<Long>,Serializable{
	
	public String getName();
	public LocationType getLocationType();
	public CoordType getCoordType();
	public boolean contains(GeoCoord coord);
}
