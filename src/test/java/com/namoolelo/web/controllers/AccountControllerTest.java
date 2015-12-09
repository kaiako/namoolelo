package com.namoolelo.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;

import com.namoolelo.domain.Location;
import com.namoolelo.domain.Moolelo;
import com.namoolelo.domain.Place;
import com.namoolelo.service.AccountService;
import com.namoolelo.service.util.MooleloList;
import com.namoolelo.web.rest.controllers.AccountController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

public class AccountControllerTest extends BaseControllerTest{

	@Mock
	AccountService accountService;
	
	@InjectMocks
	AccountController controller;
	
	private Moolelo moolelo;
	private MooleloList moolelos;
	private ArrayList<Moolelo> list;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		super.init("/rest/accounts/1/moolelos",controller);
		Location location = new Location(1F, 1F);
		Place place = new Place();
		place.setName("Test Place");
		place.setLocation(location);
		Location location2 = new Location(2F, 2F);
		Place place2 = new Place();
		place2.setName("Test Place");
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
		place.setMoolelo(moolelo);	
		place2.setMoolelo(moolelo);		
		list = new ArrayList<Moolelo>();
		list.add(moolelo);
		moolelos = new MooleloList(list);		
	}
	
	@Test
	public void testCreate() throws Exception{
		String content = "{\"title\":\"Test Moolelo\",\"text\":\"Test TEXT\",\"summary\":\"This is a test Summary\",\"estDate\":\"1500s\",\"places\":[{\"id\":null,\"name\":\"Test Place\",\"location\":{\"latitude\":1.0,\"longitude\":1.0}},{\"id\":null,\"name\":\"Test Place\",\"location\":{\"latitude\":2.0,\"longitude\":2.0}}]}";
		when(accountService.createMoolelo(anyLong(),any(Moolelo.class))).thenReturn(moolelo);
		mockMvc.perform(post(baseUrl)
			.content(content)
			.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print())
		.andExpect(status().isCreated());
	}
	
	@Test
	public void testFindAllMoolelosByAccount() throws Exception{
		when(accountService.findMoolelosByAccount(anyLong(), anyBoolean())).thenReturn(moolelos);
		mockMvc.perform(get(baseUrl))
			.andDo(print());
	}
}
