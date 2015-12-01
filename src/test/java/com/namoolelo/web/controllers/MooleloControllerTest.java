package com.namoolelo.web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.namoolelo.domain.Moolelo;
import com.namoolelo.service.MooleloService;
import com.namoolelo.web.rest.controllers.MooleloController;
import com.namoolelo.web.rest.filters.JsonResponseFilter;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.core.Is.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:mock-root-context.xml",
		"classpath:spring-hibernate.xml","classpath:spring-servlet.xml"})
@WebAppConfiguration
public class MooleloControllerTest {
	
	@Mock
	private MooleloService storyService;
	
	@Autowired
	private MooleloController controller;
	
    @Autowired
    private WebApplicationContext webApplicationContext;
 
	
	private MockMvc mockMvc;
	private Moolelo story;
	private ArrayList<Moolelo> stories;
	private String baseUrl;
	private ObjectMapper mapper = new ObjectMapper();
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.addFilter(new JsonResponseFilter(),"/*")
				.build();
		controller.setMooleloService(storyService);
		baseUrl="/rest/story";
		story = new Moolelo();
		story.setId((long)1);
		story.setTitle("Test Story");
		story.setSummary("This is a test Summary");
		story.setText("Test TEXT");
		story.setEstDate("1500s");
		story.setPlaces(null);
		stories = new ArrayList<Moolelo>();
		stories.add(story);
	}
	
	@Test
	public void testCreate() throws Exception{
		String content = mapper.writeValueAsString(story);
		mockMvc.perform(post(baseUrl+"/create")
			.content(content)
			.contentType(MediaType.APPLICATION_JSON_VALUE)) 			
		.andExpect(jsonPath("$.data", is(story.getId().intValue())))
		.andDo(print());
	}
	
	@Test
	public void testList() throws Exception{
		when(storyService.getAllStories())
		.thenReturn(stories);
		mockMvc.perform(get(baseUrl+"/list"))
		.andExpect(jsonPath("$.data[0].id", is(story.getId().intValue())))
		.andDo(print());
	}
	
	@Test
	public void testRetrieve() throws Exception {
		when(storyService.getStory(story.getId()))
		.thenReturn(story);
		mockMvc.perform(get(baseUrl+"/"+story.getId()))
		.andExpect(jsonPath("$.data.id", is(story.getId().intValue())))
		.andDo(print());
	}
	
	@Test
	public void testUpdate() throws Exception{
		String content = mapper.writeValueAsString(story);
		mockMvc.perform(post(baseUrl+"/2")
				.content(content)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.code", is(403)))
		.andDo(print());
		when(storyService.getStory(story.getId()))
		.thenReturn(story);
		mockMvc.perform(post(baseUrl+"/"+story.getId())
				.content(content)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.data", is(story.getId().intValue())))
		.andDo(print());
	}
}
