package com.namoolelo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.namoolelo.dao.StoryDaoTest;
import com.namoolelo.service.StoryServiceTest;
import com.namoolelo.web.controllers.StoryControllerTest;

@RunWith(Suite.class)
@SuiteClasses({ StoryDaoTest.class, StoryServiceTest.class, StoryControllerTest.class})
public class TestSuite {

}
