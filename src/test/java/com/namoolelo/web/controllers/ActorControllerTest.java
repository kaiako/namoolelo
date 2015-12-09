package com.namoolelo.web.controllers;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.namoolelo.domain.Actor;
import com.namoolelo.domain.Moolelo;
import com.namoolelo.service.ActorService;
import com.namoolelo.web.rest.controllers.ActorController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.core.Is.*;

public class ActorControllerTest extends BaseControllerTest{

	@Mock
	private ActorService actorService;
	
	@InjectMocks
	private ActorController controller;
	
	private Actor actor;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		super.init("/rest/actors", controller);
		HashSet<Moolelo> moolelos = new HashSet<Moolelo>();
		actor = new Actor();
		actor.setId(1L);
		actor.setDescription("description");
		actor.setName("Test Actor");
		actor.setMoolelos(moolelos);
	}
	
	@Test
	public void testFindActor() throws Exception{
		when(actorService.findActor(anyLong())).thenReturn(actor);
		mockMvc.perform(get(baseUrl+"/1"))
			.andDo(print())
			.andExpect(jsonPath("$.rid", is(1)));
	}
}
