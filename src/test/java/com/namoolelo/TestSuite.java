package com.namoolelo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.namoolelo.dao.MooleloDaoTest;
import com.namoolelo.service.MooleloServiceTest;
import com.namoolelo.web.controllers.MooleloControllerTest;

@RunWith(Suite.class)
@SuiteClasses({ MooleloDaoTest.class, MooleloServiceTest.class, MooleloControllerTest.class})
public class TestSuite {

}
