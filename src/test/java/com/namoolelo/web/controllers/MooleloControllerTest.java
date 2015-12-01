package com.namoolelo.web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.namoolelo.domain.Moolelo;
import com.namoolelo.service.MooleloService;
import com.namoolelo.service.util.MooleloList;
import com.namoolelo.web.rest.controllers.MooleloController;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.core.Is.*;

public class MooleloControllerTest {
	
	@Mock
	private MooleloService mooleloService;
	
	@InjectMocks
	private MooleloController controller;
	 
	
	private MockMvc mockMvc;
	private Moolelo moolelo;
	private MooleloList moolelos;
	private ArrayList<Moolelo> list;
	private String baseUrl;
	private ObjectMapper mapper = new ObjectMapper();
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		baseUrl="/rest/moolelos";
		moolelo = new Moolelo();
		moolelo.setId(1L);
		moolelo.setTitle("Test Moolelo");
		moolelo.setSummary("This is a test Summary");
		moolelo.setText("Test TEXT");
		moolelo.setEstDate("1500s");
		moolelo.setPlaces(null);
		list = new ArrayList<Moolelo>();
		list.add(moolelo);
		moolelos = new MooleloList(list);
	}
	
	@Test
	public void testCreate() throws Exception{
		String content = mapper.writeValueAsString(moolelo);
		when(mooleloService.createMoolelo(any())).thenReturn(moolelo);
		mockMvc.perform(post(baseUrl)
			.content(content)
			.contentType(MediaType.APPLICATION_JSON_VALUE)) 			
		.andDo(print());
	}
	
	@Test
	public void testList() throws Exception{
		when(mooleloService.findAllMoolelos()).thenReturn(moolelos);
		mockMvc.perform(get(baseUrl))
		.andExpect(jsonPath("$.data.moolelos[0].id", is(moolelo.getId().intValue())))
		.andDo(print());
	}
	
	@Test
	public void testRetrieve() throws Exception {
		when(mooleloService.getMoolelo(moolelo.getId()))
		.thenReturn(moolelo);
		mockMvc.perform(get(baseUrl+"/"+moolelo.getId()))
		.andExpect(jsonPath("$.data.id", is(moolelo.getId().intValue())))
		.andDo(print());
	}
	
	@Test
	public void testUpdate() throws Exception{
		String content = mapper.writeValueAsString(moolelo);
		mockMvc.perform(post(baseUrl+"/2")
				.content(content)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.code", is(403)))
		.andDo(print());
		when(mooleloService.getMoolelo(moolelo.getId()))
		.thenReturn(moolelo);
		mockMvc.perform(post(baseUrl+"/"+moolelo.getId())
				.content(content)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.data", is(moolelo.getId().intValue())))
		.andDo(print());
	}
}
