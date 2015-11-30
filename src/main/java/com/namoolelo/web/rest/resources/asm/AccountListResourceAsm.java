package com.namoolelo.web.rest.resources.asm;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.namoolelo.service.util.AccountList;
import com.namoolelo.web.rest.controllers.AccountController;
import com.namoolelo.web.rest.resources.AccountListResource;
import com.namoolelo.web.rest.resources.AccountResource;

public class AccountListResourceAsm extends ResourceAssemblerSupport<AccountList, AccountListResource> {

    public AccountListResourceAsm() {
        super(AccountController.class, AccountListResource.class);
    }

    @Override
    public AccountListResource toResource(AccountList accountList) {
        List<AccountResource> resList = new AccountResourceAsm().toResources(accountList.getAccounts());
        AccountListResource finalRes = new AccountListResource();
        finalRes.setAccounts(resList);
        return finalRes;
    }
}