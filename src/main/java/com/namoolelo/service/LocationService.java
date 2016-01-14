package com.namoolelo.service;

import java.util.List;

import com.namoolelo.domain.locations.Island;
import com.namoolelo.domain.locations.Moku;

public interface LocationService {

	List<Island> getAllIslands();
	List<Moku> getAllMokus();
}
