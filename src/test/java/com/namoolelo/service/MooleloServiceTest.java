package com.namoolelo.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.namoolelo.dao.MooleloDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MooleloServiceTest {

	@Mock
	MooleloDao mooleloDao;
	
	@InjectMocks
	MooleloServiceImpl storyService;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test(){
		log.info("Testing MooleloService.....");
	}
}
