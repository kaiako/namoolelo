package com.namoolelo.dao;

import java.util.List;

import com.namoolelo.domain.locations.Island;
import com.namoolelo.domain.locations.Location;
import com.namoolelo.domain.locations.Moku;

public interface LocationDao extends GenericDao<Location, Long> {

	List<Island> getAllIslands();

	List<Moku> getAllMokus();

}
