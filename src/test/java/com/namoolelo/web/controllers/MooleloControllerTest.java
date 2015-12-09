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
import com.namoolelo.domain.Account;
import com.namoolelo.domain.Location;
import com.namoolelo.domain.Moolelo;
import com.namoolelo.domain.Place;
import com.namoolelo.domain.enums.Island;
import com.namoolelo.domain.enums.Moku;
import com.namoolelo.service.MooleloService;
import com.namoolelo.service.util.MooleloList;
import com.namoolelo.web.rest.controllers.MooleloController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.core.Is.*;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(SecurityUtils.class)
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
	private Account account ;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		baseUrl="/rest/moolelos";
		account = mock(Account.class);
		Location location = new Location(1F, 1F);
		Place place = new Place();
		place.setName("Test Place 1");
		place.setLocation(location);
		Location location2 = new Location(2F, 2F);
		Place place2 = new Place();
		place2.setName("Test Place 2");
		place2.setLocation(location2);
		List<Place> places = new ArrayList<Place>();
		places.add(place);
		places.add(place2);
		moolelo = new Moolelo();
		moolelo.setId(1L);
		moolelo.setTitle("Test Moolelo");
		moolelo.setSummary("This is a test Summary");
		moolelo.setText("Test TEXT");
		moolelo.setEstDate("1500s");		
		moolelo.setPlaces(places);
		moolelo.setOwner(account);
		place.setMoolelo(moolelo);	
		place2.setMoolelo(moolelo);		
		list = new ArrayList<Moolelo>();
		list.add(moolelo);
		moolelos = new MooleloList(list);
//		PowerMockito.mockStatic(SecurityUtils.class);
//		PowerMockito.when(SecurityUtils.getAccountId()).thenReturn(1L);
	}
	
	@Test
	public void testCreatePlace() throws Exception{

		Place place3 = new Place();
		place3.setId(1L);
		place3.setName("Test Place 2");
		place3.setLocation(new Location(3F, 3F));
		place3.setIsland(Island.OAHU);
		place3.setMoku(Moku.OAHU_KOOLAULOA);
		String content = mapper.writeValueAsString(place3);
		when(account.getId()).thenReturn(1L);
		when(mooleloService.getMoolelo(1L)).thenReturn(moolelo);
		mockMvc.perform(post(baseUrl+"/1/places")
			.content(content)
			.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print())
		.andExpect(status().isCreated());
	}
	
	@Test
	public void testFindAllMoolelos() throws Exception{
		when(mooleloService.findAllMoolelos()).thenReturn(moolelos);
		mockMvc.perform(get(baseUrl))
		.andDo(print())
		.andExpect(jsonPath("$.moolelos[0].rid", is(moolelo.getId().intValue())));
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
