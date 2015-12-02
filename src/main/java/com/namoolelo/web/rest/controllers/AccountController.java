package com.namoolelo.web.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.namoolelo.domain.Account;
import com.namoolelo.domain.Moolelo;
import com.namoolelo.exceptions.AccountDoesNotExistException;
import com.namoolelo.exceptions.AccountExistsException;
import com.namoolelo.exceptions.web.ConflictException;
import com.namoolelo.exceptions.web.NotFoundException;
import com.namoolelo.service.AccountService;
import com.namoolelo.service.util.AccountList;
import com.namoolelo.service.util.MooleloList;
import com.namoolelo.web.rest.resources.AccountListResource;
import com.namoolelo.web.rest.resources.AccountResource;
import com.namoolelo.web.rest.resources.MooleloListResource;
import com.namoolelo.web.rest.resources.MooleloResource;
import com.namoolelo.web.rest.resources.asm.AccountListResourceAsm;
import com.namoolelo.web.rest.resources.asm.AccountResourceAsm;
import com.namoolelo.web.rest.resources.asm.MooleloListResourceAsm;
import com.namoolelo.web.rest.resources.asm.MooleloResourceAsm;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Chris on 6/28/14.
 */
@Slf4j
@RestController
@RequestMapping("/rest/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<AccountListResource> findAllAccounts(@RequestParam(value="name", required = false) String name) {
        AccountList list = null;
        if(name == null) {
            list = accountService.findAllAccounts();
        } else {
            Account account = accountService.findByAccountUsername(name);
            if(account == null) {
                list = new AccountList(new ArrayList<Account>());
            } else {
                list = new AccountList(Arrays.asList(account));
            }
        }
        AccountListResource res = new AccountListResourceAsm().toResource(list);
        return new ResponseEntity<AccountListResource>(res, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountResource> createAccount(
            @RequestBody AccountResource sentAccount) {
        try {
            Account createdAccount = accountService.createAccount(sentAccount.toAccount());
            AccountResource res = new AccountResourceAsm().toResource(createdAccount);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<AccountResource>(res, headers, HttpStatus.CREATED);
        } catch(AccountExistsException exception) {
            throw new ConflictException(exception);
        }
    }

    @RequestMapping( value="/{accountId}",
                method = RequestMethod.GET)
    public ResponseEntity<AccountResource> getAccount(
            @PathVariable Long accountId
    ) {
        Account account = accountService.findAccount(accountId);
        if(account != null)
        {
            AccountResource res = new AccountResourceAsm().toResource(account);
            return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
        }
    }
    


	@RequestMapping(value="/{accountId}/moolelos",
			method=RequestMethod.POST)
	public ResponseEntity<MooleloResource> createMoolelo(@PathVariable long accountId, 
			@RequestBody MooleloResource moolelo){
		log.info("Creating Moolelo " + moolelo.getTitle());
        try {
            Moolelo createdMoolelo = accountService.createMoolelo(accountId,moolelo.toMoolelo());
            MooleloResource res = new MooleloResourceAsm().toResource(createdMoolelo);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<MooleloResource>(res, headers, HttpStatus.CREATED);
        } catch(AccountExistsException exception) {
            throw new ConflictException(exception);
        }
	}

    @RequestMapping(value="/{accountId}/moolelos",
            method = RequestMethod.GET)
    public ResponseEntity<MooleloListResource> findAllMoolelos(
            @PathVariable Long accountId) {
        try {
            MooleloList mooleloList = accountService.findMoolelosByAccount(accountId, true);
            MooleloListResource mooleloListRes = new MooleloListResourceAsm().toResource(mooleloList);
            return new ResponseEntity<MooleloListResource>(mooleloListRes, HttpStatus.OK);
        } catch(AccountDoesNotExistException exception)
        {
            throw new NotFoundException(exception);
        }
    }



}