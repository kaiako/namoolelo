package com.namoolelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.namoolelo.dao.LocationDao;
import com.namoolelo.domain.locations.Island;
import com.namoolelo.domain.locations.Moku;

public class LocationServiceImpl implements LocationService {

	@Autowired
	LocationDao locationDao;
	
	@Override
	public List<Island> getAllIslands() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Moku> getAllMokus() {
		// TODO Auto-generated method stub
		return null;
	}

}