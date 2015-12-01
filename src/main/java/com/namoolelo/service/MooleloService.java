package com.namoolelo.service;

import com.namoolelo.domain.Moolelo;
import com.namoolelo.service.util.MooleloList;

public interface MooleloService {

	Moolelo getMoolelo(long id);
	Moolelo createMoolelo(Moolelo moolelo);
	void saveOrUpdate(Moolelo moolelo);
	void delete(Moolelo moolelo);
	MooleloList findAllMoolelos();
}
