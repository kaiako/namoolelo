package com.namoolelo.service;

import com.namoolelo.domain.Account;

public interface AccountService {

	Account findByAccountUsername(String username);

}
