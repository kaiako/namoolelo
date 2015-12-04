package com.namoolelo.service;

import com.namoolelo.domain.Moolelo;
import com.namoolelo.exceptions.web.NotFoundException;
import com.namoolelo.service.util.MooleloList;

public interface MooleloService {

	Moolelo getMoolelo(long id) throws NotFoundException;
	MooleloList findMoolelosByTitle(String title, boolean includePlaces);
	void saveOrUpdate(Moolelo moolelo);
	void delete(Moolelo moolelo);
	MooleloList findAllMoolelos();
}
