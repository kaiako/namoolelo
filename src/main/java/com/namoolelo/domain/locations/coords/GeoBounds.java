package com.namoolelo.domain.locations.coords;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class GeoBounds {
	private GeoCoord northeast;
	private GeoCoord southwest;

	public GeoBounds(GeoCoord northeast, GeoCoord southwest) {
		this.northeast = northeast;
		this.southwest = southwest;
	}
}
