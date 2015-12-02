package com.namoolelo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namoolelo.dao.AccountDao;
import com.namoolelo.dao.MooleloDao;
import com.namoolelo.domain.Account;
import com.namoolelo.domain.Moolelo;
import com.namoolelo.exceptions.AccountDoesNotExistException;
import com.namoolelo.exceptions.AccountExistsException;
import com.namoolelo.exceptions.MooleloExistsException;
import com.namoolelo.service.util.AccountList;
import com.namoolelo.service.util.MooleloList;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private MooleloDao mooleloDao;

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

	@Override
	public Moolelo createMoolelo(long accountId, Moolelo moolelo) {
		Moolelo mooleloSameTitle = mooleloDao.findMooleloByTitle(moolelo.getTitle(), false);

        if(mooleloSameTitle != null)
        {
            throw new  MooleloExistsException();
        }

        Account account = accountDao.find(accountId);
        if(account == null)
        {
            throw new AccountDoesNotExistException();
        }

        mooleloDao.saveOrUpdate(moolelo);
        moolelo.setOwner(account);

        return moolelo;
    }

	@Override
	public MooleloList findMoolelosByAccount(Long accountId, boolean includePlaces) {
        Account account = accountDao.find(accountId);
        if(account == null)
        {
            throw new AccountDoesNotExistException();
        }
		return mooleloDao.findAllMoolelosByAccount(accountId,includePlaces);
	}

	

}
