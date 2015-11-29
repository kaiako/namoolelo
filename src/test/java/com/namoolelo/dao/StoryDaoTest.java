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
import com.namoolelo.domain.Story;
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
public class StoryDaoTest {
	
	@Autowired
	StoryDao storyDao;
	
	@Test
	public void testFind(){
		Story story = storyDao.find((long)1);
		assertNotNull(story);
		assertTrue(story.getId() == 1);
		assertTrue(story.getTitle().equals("Test Mo'olelo 1"));
		assertTrue(story.getPlaces().size() == 3);
	}
	
	@Test
	public void testGetAll(){
		List<Story> stories = storyDao.getAll();
		assertTrue(stories.size() == 3);
	}
	
	@Test
	public void testSaveAndDelete(){
		int size = storyDao.getAll().size();
		ArrayList<Place> places = new ArrayList<Place>();
		Place place = new Place();
		place.setLocation(new Location((float)1.0,(float)1.0));
		place.setName("Test Save Place");
		places.add(place);
		Story story = new Story();
		story.setTitle("Test Save Mo'olelo");
		story.setEstDate("November 28, 2015");
		story.setSummary("Test Save Summary");
		story.setText("Test Save Text");
		story.setPlaces(places);
		place.setStory(story);
		storyDao.save(story);
		assertTrue(storyDao.getAll().size() == (size+1));
		storyDao.delete(story);
		assertTrue(storyDao.getAll().size() == (size));		
	}
	
	@Test
	public void testSaveOrUpdate(){
		String titleChange = "Test Update Mo'olelo";
		Story story = storyDao.find((long)1);
		story.setTitle(titleChange);
		storyDao.saveOrUpdate(story);
		Story updatedStory = storyDao.find((long)1);
		assertTrue(titleChange.equals(updatedStory.getTitle()));
	}

}
