package com.namoolelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namoolelo.dao.MooleloDao;
import com.namoolelo.domain.Moolelo;

@Service
public class MooleloServiceImpl implements MooleloService {

	@Autowired
	private MooleloDao mooleloDao;

	@Override
	public Moolelo getStory(long id) {
		return mooleloDao.find(id);
	}

	@Override
	public void saveOrUpdate(Moolelo moolelo) {
		mooleloDao.saveOrUpdate(moolelo);
	}

	@Override
	public void delete(Moolelo moolelo) {
		mooleloDao.delete(moolelo);
	}

	@Override
	public List<Moolelo> getAllStories() {
		return mooleloDao.getAll();
	}
	
}
