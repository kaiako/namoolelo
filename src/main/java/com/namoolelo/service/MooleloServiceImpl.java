package com.namoolelo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namoolelo.dao.MooleloDao;
import com.namoolelo.domain.Moolelo;
import com.namoolelo.service.util.MooleloList;

@Service
public class MooleloServiceImpl implements MooleloService {

	@Autowired
	private MooleloDao mooleloDao;

	@Override
	public Moolelo getMoolelo(long id) {
		return mooleloDao.find(id);
	}

	@Override
	public void saveOrUpdate(Moolelo moolelo) {
		mooleloDao.saveOrUpdate(moolelo);
	}

	@Override
	public void delete(Moolelo moolelo) {
		mooleloDao.delete(moolelo);
	}

	@Override
	public MooleloList getAllMoolelos() {
		return new MooleloList(mooleloDao.getAll());
	}
	
}
