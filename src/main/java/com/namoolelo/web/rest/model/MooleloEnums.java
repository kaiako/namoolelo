package com.namoolelo.web.rest.model;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.namoolelo.domain.enums.Island;
import com.namoolelo.web.rest.model.serializers.MooleloEnumsSerializer;

@Getter
@JsonSerialize(using=MooleloEnumsSerializer.class)
public class MooleloEnums {
	
	@JsonIgnore
	private static MooleloEnums instance = null;
	
	private List<Island> islands;
	
	private MooleloEnums(){
		islands = Arrays.asList(Island.values());
	}
	
	public static MooleloEnums getInstance(){
		if(instance == null){
			instance = new MooleloEnums();
		}
		return instance;
	}
}
