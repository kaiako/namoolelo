package com.namoolelo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namoolelo.dao.AccountDao;
import com.namoolelo.domain.Account;
import com.namoolelo.exceptions.AccountExistsException;
import com.namoolelo.service.util.AccountList;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountDao accountDao;

	@Override
	public Account findByAccountUsername(String username) {
		return accountDao.findByAccountUsername(username);
	}

	@Override
	public Account findAccount(Long accountId) {
		return accountDao.find(accountId);
	}

	@Override
	public AccountList findAllAccounts() {
		return new AccountList(accountDao.getAll());
	}

	@Override
	public Account createAccount(Account account) {
        Account foundAccount = accountDao.findByAccountUsername(account.getUsername());
        if(foundAccount != null)
        {
            throw new AccountExistsException();
        }
		accountDao.saveOrUpdate(account);
		return account;
	}
	

}
