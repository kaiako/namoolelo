package com.namoolelo.domain.locations;

import java.io.Serializable;

import com.namoolelo.domain.GeoCoord;
import com.namoolelo.domain.enums.CoordType;
import com.namoolelo.domain.enums.LocationType;

public interface Location extends Serializable{
	
	public String getName();
	public boolean getLocationType(LocationType locationType);
	public boolean contains(GeoCoord coords);
	public boolean getCoordType(CoordType coordType);
}
