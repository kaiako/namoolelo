package com.namoolelo.domain.enums;

public enum Moku {
//	O'ahu Mokus
	OAHU_WAIANAE("Wai'anae"),
	OAHU_WAIALUA("Waialua"),
	OAHU_KOOLAULOA("Ko'olauloa"),
	OAHU_KOOLAUPOKO("Ko'olaupoko"),
	OAHU_EWA("'Ewa"),
	OAHU_KONA("Kona"),
	
//  Hawaii Mokus
	HAWAII_KOHALA("Kohala"),
	HAWAII_HAMAKUA("Hamakua"),
	HAWAII_HILO("Hilo"),
	HAWAII_PUNA("Puna"),
	HAWAII_KAU("Ka'u"),
	HAWAII_KONA("Kona"),

//	Maui Mokus
	MAUI_HAMAKUAPOKO("Hamakuapoko"),
	MAUI_HAMAKUALOA("Hamakualoa"),
	MAUI_KOOLAU("Ko'olau"),
	MAUI_HANA("Hana"),
	MAUI_KIPAHULU("Kipahulu"),
	MAUI_KAUPO("Kaupo"),
	MAUI_KAHIKINUI("Kahikinui"),
	MAUI_HONUAULA("Honuaula"),
	MAUI_KULA("Kula"),
	MAUI_KEALALOLOA("Kealaloloa"),
	MAUI_LAHINA("Lahina"),
	MAUI_KAANAPALI("Ka'anapali"),
	MAUI_WAILUKU("Wailuku"),
	
//	Kaho'olawe Mokus
	KAHOOLAWE_KONA("Kona"),
	KAHOOLAWE_KOOLAU("Ko'olau"),
	KAHOOLAWE_HONUAULA("Honuaula"),
	
//	Lana'i Mokus
	LANAI_KONA("Kona"),
	LANAI_KOOLAU("Ko'olau"),
	
//	Moloka'i Mokus
	MOLOKAI_KALUAKOI("Kaluakoi"),
	MOLOKAI_KOOLAU("Ko'olau"),
	MOLOKAI_HALAWA("Halawa"),
	MOLOKAI_KONA("Kona"),
	MOLOKAI_PALAAU("Pala'au"),
	
//	Kauai'i Mokus
	KAUAI_KONA("Kona"),
	KAUAI_MANA("Mana"),
	KAUAI_NAPALI("Napali"),
	KAUAI_HALELEA("Halele'a"),
	KAUAI_KOOLAU("Ko'olau"),
	KAUAI_PUNA("Puna"),

//	Ni'ihau Mokus
	NIIHAU_KOOLAU("Koolau"),
	NIIHAU_PUNA("Puna");	
	
	private String name;
	
	Moku(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public static Moku[] getMokuByIsland(Island island){
		switch(island){
		case HAWAII:
			return new Moku[]{ 
					HAWAII_KOHALA,
					HAWAII_HAMAKUA,
					HAWAII_HILO,
					HAWAII_PUNA,
					HAWAII_KAU,
					HAWAII_KONA
			};
		case KAHOOLAWE:
			return new Moku[]{ 
					KAHOOLAWE_HONUAULA,
					KAHOOLAWE_KONA,
					KAHOOLAWE_KOOLAU
			};
		case KAUAI:
			return new Moku[]{ 
					KAUAI_KONA,
					KAUAI_MANA,
					KAUAI_NAPALI,
					KAUAI_HALELEA,
					KAUAI_KOOLAU,
					KAUAI_PUNA					
			};
		case LANAI:
			return new Moku[]{ 
					LANAI_KONA,
					LANAI_KOOLAU					
			};
		case MAUI:
			return new Moku[]{ 	
					MAUI_HAMAKUAPOKO,
					MAUI_HAMAKUALOA,
					MAUI_KOOLAU,
					MAUI_HANA,
					MAUI_KIPAHULU,
					MAUI_KAUPO,
					MAUI_KAHIKINUI,
					MAUI_HONUAULA,
					MAUI_KULA,
					MAUI_KEALALOLOA,
					MAUI_LAHINA,
					MAUI_KAANAPALI,
					MAUI_WAILUKU				
			};
		case MOLOKAI:
			return new Moku[]{ 		
					MOLOKAI_KALUAKOI,
					MOLOKAI_KOOLAU,
					MOLOKAI_HALAWA,
					MOLOKAI_KONA,
					MOLOKAI_PALAAU		
			};
		case NIIHAU:
			return new Moku[]{ 		
					NIIHAU_KOOLAU,
					NIIHAU_PUNA				
			};
		case OAHU:
			return new Moku[]{ 	
					OAHU_WAIANAE,
					OAHU_WAIALUA,
					OAHU_KOOLAULOA,
					OAHU_KOOLAUPOKO,
					OAHU_EWA,
					OAHU_KONA				
			};
		default:
			return null;
		
		}
	}
}
