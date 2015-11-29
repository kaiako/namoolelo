package com.namoolelo.service;

import java.util.List;

import com.namoolelo.domain.Story;

public interface StoryService {

	Story getStory(long id);
	void saveOrUpdate(Story story);
	void delete(Story story);
	List<Story> getAllStories();
}
