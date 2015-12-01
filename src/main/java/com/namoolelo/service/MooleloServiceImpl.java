package com.namoolelo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.namoolelo.dao.MooleloDao;
import com.namoolelo.domain.Moolelo;
import com.namoolelo.exceptions.MooleloExistsException;
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
	public Moolelo createMoolelo(Moolelo moolelo) throws MooleloExistsException {
		Assert.notNull(moolelo);
		if(moolelo.getId() != null){
			throw new MooleloExistsException();
		}
		saveOrUpdate(moolelo);
		return moolelo;
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
