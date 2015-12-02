package com.namoolelo.dao;

import java.util.List;

import com.namoolelo.domain.Moolelo;
import com.namoolelo.service.util.MooleloList;

public interface MooleloDao extends GenericDao<Moolelo, Long> {

	Moolelo find(Long id, boolean includePlaces);

	Moolelo findMooleloByTitle(String title, boolean includePlaces);
	
	List<Moolelo> findMoolelosByTitle(String title, boolean includePlaces);

	MooleloList findAllMoolelosByAccount(Long accountId, boolean includePlaces);

}
