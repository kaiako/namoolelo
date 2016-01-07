package com.namoolelo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.namoolelo.dbunit.XLSDataSetLoader;
import com.namoolelo.domain.Location;
import com.namoolelo.domain.Place;
import com.namoolelo.domain.Moolelo;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:mock-root-context.xml",
		"classpath:spring-hibernate.xml","classpath:spring-servlet.xml"})
@DbUnitConfiguration(dataSetLoader=XLSDataSetLoader.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, 
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class})
@DatabaseSetup("classpath:base_data.xls")
public class MooleloDaoTest {
	
	@Autowired
	MooleloDao mooleloDao;
	
	@Test
	public void testFind(){
		Moolelo moolelo = mooleloDao.find(1L);
		assertNotNull(moolelo);
		assertTrue(moolelo.getId() == 1);
		assertTrue(moolelo.getTitle().equals("Test Mo'olelo 1"));
		assertTrue(moolelo.getPlaces().size() == 3);
	}
	
	@Test
	public void testGetAll(){
		List<Moolelo> moolelos = mooleloDao.getAll();
		assertTrue(moolelos.size() == 3);
	}
	
	@Test
	public void testSaveAndDelete(){
		int size = mooleloDao.getAll().size();
		ArrayList<Place> places = new ArrayList<Place>();
		Place place = new Place();
		place.setLocation(new Location(1.0F,1.0F, 1));
		place.setName("Test Save Place");
		places.add(place);
		Moolelo moolelo = new Moolelo();
		moolelo.setTitle("Test Save Mo'olelo");
		moolelo.setEstDate("November 28, 2015");
		moolelo.setSummary("Test Save Summary");
		moolelo.setText("Test Save Text");
		moolelo.setPlaces(places);
		place.setMoolelo(moolelo);
		mooleloDao.save(moolelo);
		assertTrue(mooleloDao.getAll().size() == (size+1));
		mooleloDao.delete(moolelo);
		assertTrue(mooleloDao.getAll().size() == (size));		
	}
	
	@Test
	public void testSaveOrUpdate(){
		String titleChange = "Test Update Mo'olelo";
		Moolelo moolelo = mooleloDao.find(1L);
		moolelo.setTitle(titleChange);
		mooleloDao.saveOrUpdate(moolelo);
		Moolelo updatedStory = mooleloDao.find(1L);
		assertTrue(titleChange.equals(updatedStory.getTitle()));
	}

}
