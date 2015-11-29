package com.namoolelo.dao;

import com.namoolelo.domain.Account;

public interface AccountDao extends GenericDao<Account, Long> {

	Account findByAccountUsername(String username);

}
