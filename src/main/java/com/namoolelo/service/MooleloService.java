package com.namoolelo.service;

import java.util.List;

import com.namoolelo.domain.Moolelo;

public interface MooleloService {

	Moolelo getStory(long id);
	void saveOrUpdate(Moolelo moolelo);
	void delete(Moolelo moolelo);
	List<Moolelo> getAllStories();
}
