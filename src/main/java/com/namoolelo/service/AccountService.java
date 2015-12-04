package com.namoolelo.service;

import com.namoolelo.domain.Account;
import com.namoolelo.domain.Moolelo;
import com.namoolelo.exceptions.AccountDoesNotExistException;
import com.namoolelo.exceptions.MooleloExistsException;
import com.namoolelo.service.util.AccountList;
import com.namoolelo.service.util.MooleloList;

public interface AccountService {

	Account findByAccountUsername(String username);

	Account findAccount(Long accountId)throws AccountDoesNotExistException;

	AccountList findAllAccounts();

	Account createAccount(Account account);
	
	Moolelo createMoolelo(long accountId, Moolelo moolelo)throws AccountDoesNotExistException, MooleloExistsException;

	MooleloList findMoolelosByAccount(Long accountId, boolean includePlaces)throws AccountDoesNotExistException;

	MooleloList findMyMoolelos(boolean includePlaces) throws AccountDoesNotExistException;

}
