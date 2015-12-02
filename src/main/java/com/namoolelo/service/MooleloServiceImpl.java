package com.namoolelo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
	public MooleloList findMoolelosByTitle(String title, boolean includePlaces){
		return new MooleloList(mooleloDao.findMoolelosByTitle(title,includePlaces));
	}
	
	@Override
	public void saveOrUpdate(Moolelo moolelo) {
		Assert.notNull(moolelo);
		mooleloDao.saveOrUpdate(moolelo);
	}

	@Override
	public void delete(Moolelo moolelo) {
		Assert.notNull(moolelo);
		mooleloDao.delete(moolelo);
	}

	@Override
	public MooleloList findAllMoolelos() {
		return new MooleloList(mooleloDao.getAll());
	}
}
