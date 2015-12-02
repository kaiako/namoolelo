package com.namoolelo.web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.namoolelo.service.PlaceService;
import com.namoolelo.web.rest.controllers.PlaceController;


public class PlaceControllerTest {

	@Mock
	PlaceService placeService;
	
	@InjectMocks
	PlaceController controller;
	
	MockMvc mockMvc;
	
	private String baseUrl;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		baseUrl = "/rest/moolelos/1/places";		
	}
	
	@Test
	public void testCreate(){
		
		
	}
}
