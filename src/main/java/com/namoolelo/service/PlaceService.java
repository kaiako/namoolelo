package com.namoolelo.service;

import com.namoolelo.domain.Place;
import com.namoolelo.exceptions.IncorrectOwnerException;
import com.namoolelo.service.util.PlaceList;

public interface PlaceService {

	Place findPlace(Long placeId);

	PlaceList findAllPlaces();

	Place updatePlace(Place place);

	Place createPlace(long mooleloId, Place place) throws IncorrectOwnerException;

}
