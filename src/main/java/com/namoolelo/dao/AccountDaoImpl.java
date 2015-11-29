package com.namoolelo.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.namoolelo.domain.Account;

@Repository
public class AccountDaoImpl extends GenericDaoImpl<Account, Long> implements AccountDao{

	@Override
	public Account findByAccountUsername(String username) {
		return (Account) getSessionFactory().getCurrentSession().createCriteria(Account.class)
				.add(Restrictions.eq("username",username))
				.uniqueResult();
	}

}
