package com.namoolelo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namoolelo.dao.PlaceDao;
import com.namoolelo.domain.Moolelo;
import com.namoolelo.domain.Place;
import com.namoolelo.exceptions.IncorrectOwnerException;
import com.namoolelo.security.SecurityUtils;
import com.namoolelo.service.util.PlaceList;

@Service
public class PlaceServiceImpl implements PlaceService{

	@Autowired
	private PlaceDao placeDao;
	
	@Autowired
	private MooleloService mooleloService;
	
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

	@Override
	public Place updatePlace(Place place) {
		placeDao.saveOrUpdate(place);
		return place;
	}

	@Override
	public Place createPlace(long mooleloId, Place place) 
		throws IncorrectOwnerException{
		Moolelo moolelo =  mooleloService.getMoolelo(mooleloId);
		if(moolelo.getOwner().getId() != SecurityUtils.getAccountId()){
			throw new IncorrectOwnerException();
		}
		moolelo.getPlaces().add(place);
		place.setMoolelo(moolelo);
		mooleloService.saveOrUpdate(moolelo);
		return place;
		
	}

}
