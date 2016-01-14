package com.namoolelo.domain.locations;

import java.io.Serializable;

import com.namoolelo.domain.enums.CoordType;
import com.namoolelo.domain.enums.LocationType;
import com.namoolelo.domain.locations.coords.GeoCoord;

public interface Location extends Serializable{
	
	public Long getId();
	public String getName();
	public LocationType getLocationType();
	public boolean contains(GeoCoord coords);
	public CoordType getCoordType();
}
