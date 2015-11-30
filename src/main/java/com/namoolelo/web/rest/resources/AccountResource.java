package com.namoolelo.web.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.namoolelo.domain.Account;

public class AccountResource extends ResourceSupport {
	
    private String username;

    private String password;

    private Long rid;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }
    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public Account toAccount() {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return account;
    }
}
