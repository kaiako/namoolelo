package com.namoolelo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.namoolelo.dao.MooleloDaoTest;
import com.namoolelo.service.MooleloServiceTest;
import com.namoolelo.web.controllers.AccountControllerTest;
import com.namoolelo.web.controllers.ActorControllerTest;
import com.namoolelo.web.controllers.MooleloControllerTest;
import com.namoolelo.web.controllers.PlaceControllerTest;

@RunWith(Suite.class)
@SuiteClasses({ MooleloDaoTest.class, MooleloServiceTest.class, MooleloControllerTest.class,
	AccountControllerTest.class, ActorControllerTest.class, PlaceControllerTest.class})
public class TestSuite {

}
