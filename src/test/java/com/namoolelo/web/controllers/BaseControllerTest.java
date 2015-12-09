package com.namoolelo.web.controllers;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public abstract class BaseControllerTest {
	
	protected MockMvc mockMvc;
	protected String baseUrl;
	
	protected void init(String baseUrl,Object controller){
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		this.baseUrl = baseUrl;
	}

}
