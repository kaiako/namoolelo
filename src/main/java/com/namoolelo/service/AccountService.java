package com.namoolelo.service;

import com.namoolelo.domain.Account;
import com.namoolelo.web.controllers.AccountList;

public interface AccountService {

	Account findByAccountUsername(String username);

	Account findAccount(Long accountId);

	AccountList findAllAccounts();

}
