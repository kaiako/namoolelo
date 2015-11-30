package com.namoolelo.web.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.namoolelo.domain.Account;
import com.namoolelo.web.rest.controllers.AccountController;
import com.namoolelo.web.rest.resources.AccountResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResource> {
    public AccountResourceAsm() {
        super(AccountController.class, AccountResource.class);
    }

    @Override
    public AccountResource toResource(Account account) {
        AccountResource res = new AccountResource();
        res.setName(account.getUsername());
        res.setPassword(account.getPassword());
        res.setRid(account.getId());
        res.add(linkTo(methodOn(AccountController.class).getAccount(account.getId())).withSelfRel());
        return res;
    }
}
