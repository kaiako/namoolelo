package com.namoolelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namoolelo.dao.StoryDao;
import com.namoolelo.domain.Story;

@Service
public class StoryServiceImpl implements StoryService {

	@Autowired
	private StoryDao storyDao;

	@Override
	public Story getStory(long id) {
		return storyDao.find(id);
	}

	@Override
	public void saveOrUpdate(Story story) {
		storyDao.saveOrUpdate(story);
	}

	@Override
	public void delete(Story story) {
		storyDao.delete(story);
	}

	@Override
	public List<Story> getAllStories() {
		return storyDao.getAll();
	}
	
}
