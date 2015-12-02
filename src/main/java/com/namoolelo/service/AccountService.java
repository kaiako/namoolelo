package com.namoolelo.service;

import com.namoolelo.domain.Account;
import com.namoolelo.domain.Moolelo;
import com.namoolelo.service.util.AccountList;
import com.namoolelo.service.util.MooleloList;

public interface AccountService {

	Account findByAccountUsername(String username);

	Account findAccount(Long accountId);

	AccountList findAllAccounts();

	Account createAccount(Account account);
	
	Moolelo createMoolelo(long accountId, Moolelo moolelo);

	MooleloList findMoolelosByAccount(Long accountId, boolean includePlaces);

}
