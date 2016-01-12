package com.namoolelo.web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.namoolelo.domain.Moolelo;
import com.namoolelo.domain.Place;
import com.namoolelo.service.PlaceService;
import com.namoolelo.service.util.PlaceList;
import com.namoolelo.web.rest.controllers.PlaceController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.core.Is.*;

public class PlaceControllerTest {

	@Mock
	PlaceService placeService;
	
	@InjectMocks
	PlaceController controller;
	
	MockMvc mockMvc;
	
	private String baseUrl;
	private Place place;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		baseUrl = "/rest/places";
		place = new Place();
		place.setId(1L);
		place.setName("Kaneohe");
		place.setMoolelo(mock(Moolelo.class));
	}
	
	@Test
	public void testFindAllPlaces() throws Exception{
		when(placeService.findAllPlaces()).thenReturn(mock(PlaceList.class));
		mockMvc.perform(get(baseUrl))
			.andDo(print());		
	}
	
	@Test
	public void testFindPlace() throws Exception{
		when(placeService.findPlace(any())).thenReturn(place);
		mockMvc.perform(get(baseUrl+"/1"))
			.andDo(print())
			.andExpect(jsonPath("$.rid", is(1)));
	}
}
