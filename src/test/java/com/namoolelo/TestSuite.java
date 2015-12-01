package com.namoolelo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.namoolelo.dao.MooleloDaoTest;
import com.namoolelo.service.StoryServiceTest;
import com.namoolelo.web.controllers.MooleloControllerTest;

@RunWith(Suite.class)
@SuiteClasses({ MooleloDaoTest.class, StoryServiceTest.class, MooleloControllerTest.class})
public class TestSuite {

}
