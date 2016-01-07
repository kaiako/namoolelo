package com.namoolelo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.namoolelo.domain.Moolelo;
import com.namoolelo.service.util.MooleloList;

@Repository
@Transactional
public class MooleloDaoImpl extends GenericDaoImpl<Moolelo, Long> implements MooleloDao {

	@Autowired
	SessionFactory sessionFactory;
	
	private Criteria getMooleloCriteria(boolean includePlaces){
		Criteria criteria = getCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		if(includePlaces){
			criteria.setFetchMode("places", FetchMode.JOIN);
		}
		return criteria;
	}
	
	@Override
	public Moolelo find(Long id) {
		Moolelo moolelo = super.find(id);
		Hibernate.initialize(moolelo.getPlaces());
		return moolelo;
	}

	@Override
	public Moolelo findMooleloByTitle(String title, boolean includePlaces) {
		return (Moolelo) getMooleloCriteria(includePlaces)
				.add(Restrictions.eq("title", title))
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Moolelo> findMoolelosByTitle(String title, boolean includePlaces) {
		return getMooleloCriteria(includePlaces)
				.add(Restrictions.like("%title%", title))
				.list();
				
	}

	@Override
	public Moolelo find(Long id, boolean includePlaces) {
		Moolelo moolelo = find(id);
		Hibernate.initialize(moolelo.getPlaces());
		return moolelo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MooleloList findAllMoolelosByAccount(Long accountId, boolean includePlaces) {
		return new MooleloList(getMooleloCriteria(includePlaces)
				.add(Restrictions.eq("owner.id",accountId))
				.list());
	}
}
