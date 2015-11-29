package com.namoolelo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namoolelo.dao.AccountDao;
import com.namoolelo.domain.Account;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountDao accountDao;

	@Override
	public Account findByAccountUsername(String username) {
		return accountDao.findByAccountUsername(username);
	}
	

}
