package com.namoolelo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namoolelo.dao.PlaceDao;
import com.namoolelo.domain.Place;
import com.namoolelo.service.util.PlaceList;

@Service
public class PlaceServiceImpl implements PlaceService{

	@Autowired
	private PlaceDao placeDao;
	
	@Override
	public Place findPlace(Long placeId) {
		return placeDao.find(placeId);
	}

	@Override
	public PlaceList findAllPlaces() {
		PlaceList list = new PlaceList();
		list.setPlaces(placeDao.getAll());
		return list;
	}

}
