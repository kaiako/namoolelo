package com.namoolelo.service;

import com.namoolelo.domain.Account;
import com.namoolelo.service.util.AccountList;

public interface AccountService {

	Account findByAccountUsername(String username);

	Account findAccount(Long accountId);

	AccountList findAllAccounts();

	Account createAccount(Account account);

}
