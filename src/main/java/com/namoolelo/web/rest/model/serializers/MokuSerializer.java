package com.namoolelo.web.rest.model.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.namoolelo.domain.enums.Moku;

public class MokuSerializer extends JsonSerializer<Moku>{

	@Override
	public void serialize(Moku value, JsonGenerator gen,
			SerializerProvider serializers) throws IOException,
			JsonProcessingException {
		gen.writeStartObject();
		gen.writeStringField("name", value.getName());
		gen.writeStringField("value", value.toString());
		gen.writeEndObject();
		
	}

}
