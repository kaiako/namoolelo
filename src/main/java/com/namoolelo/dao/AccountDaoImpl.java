package com.namoolelo.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.namoolelo.domain.Account;

@Repository
@Transactional
public class AccountDaoImpl extends GenericDaoImpl<Account, Long> implements AccountDao{

	@Override
	public Account findByAccountUsername(String username) {
		return (Account) getCriteria()
				.add(Restrictions.eq("username",username))
				.uniqueResult();
	}

}
