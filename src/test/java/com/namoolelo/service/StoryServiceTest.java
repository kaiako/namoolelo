package com.namoolelo.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.namoolelo.dao.StoryDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StoryServiceTest {

	@Mock
	StoryDao storyDao;
	
	@InjectMocks
	StoryServiceImpl storyService;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test(){
		log.info("Testing StoryService.....");
	}
}
