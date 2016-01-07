package com.namoolelo.web.rest.model.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.namoolelo.domain.enums.Island;
import com.namoolelo.domain.enums.Moku;
import com.namoolelo.web.rest.model.MooleloEnums;

public class MooleloEnumsSerializer extends JsonSerializer<MooleloEnums>{

	@Override
	public void serialize(MooleloEnums value, JsonGenerator gen,
			SerializerProvider serializers) throws IOException,
			JsonProcessingException {

		gen.writeStartObject();
		
		gen.writeFieldName("islands");
		gen.writeStartObject();
		for(Island island : value.getIslands()){
			gen.writeObjectField(island.toString(), island);			
		}
		gen.writeEndObject();

		gen.writeFieldName("mokus");
		gen.writeStartObject();
		for(Island island : value.getIslands()){
			gen.writeFieldName(island.toString());
			gen.writeStartArray();
			for(Moku moku : Moku.getMokuByIsland(island)){
				gen.writeObject(moku);
			}
			gen.writeEndArray();
		}
		gen.writeEndObject();
		
		gen.writeEndObject();
	}

}
