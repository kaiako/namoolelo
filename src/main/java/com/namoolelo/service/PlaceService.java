package com.namoolelo.service;

import com.namoolelo.domain.Place;
import com.namoolelo.service.util.PlaceList;

public interface PlaceService {

	Place findPlace(Long placeId);

	PlaceList findAllPlaces();

}
