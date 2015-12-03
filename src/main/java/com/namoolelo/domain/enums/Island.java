package com.namoolelo.domain.enums;

public enum Island {
	HAWAII("Hawai'i"),
	MAUI("Maui"),
	KAHOOLAWE("Kaho'olawi"),
	LANAI("Lana'i"),
	MOLOKAI("Moloka'i"),
	OAHU("O'ahu"),
	KAUAI("Kaua'i"),
	NIIHAU("Ni'ihau");
	
	private String name;
	
	Island(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
}
