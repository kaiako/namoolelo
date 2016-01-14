package com.namoolelo.domain.enums;

import lombok.Getter;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.namoolelo.domain.locations.support.GeoCoord;
import com.namoolelo.web.rest.model.serializers.IslandSerializer;

@Getter
@JsonSerialize(using=IslandSerializer.class)
public enum Island {
	HAWAII("Hawai'i",19.5429151F, -155.66585680000003F, 8),
	MAUI("Maui",20.7983626F, -156.33192529999997F, 9),
	KAHOOLAWE("Kaho'olawe",20.5580469F, -156.60573780000004F, 11),
	LANAI("Lana'i",20.8165975F, -156.92731930000002F,10),
	MOLOKAI("Moloka'i",21.1443935F, -157.02262970000004F, 11),
	OAHU("O'ahu",21.4389123F, -158.00005650000003F, 9),
	KAUAI("Kaua'i",22.0964396F, -159.5261238F, 10),
	NIIHAU("Ni'ihau",21.8921433F, -160.1574878F, 10);
	
	private String name;
	private GeoCoord location;
	
	Island(String name, float lat, float lng, int zoom){
		this.name = name;
		this.location = new GeoCoord(lat, lng);
	}
	
}
