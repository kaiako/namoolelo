package com.namoolelo.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.namoolelo.domain.Account;

@Component
public class SecurityUtils {
	
	public static AccountUserDetails getUserDetails(){
		AccountUserDetails userDetails = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
		         userDetails = (AccountUserDetails) auth.getPrincipal();
		}
		return userDetails;
	}
	
	public static Account getAccount(){
		Account account = null;
		AccountUserDetails details = getUserDetails();
		if(details != null){
			account = details.getAccount();
		}
		return account;
	}
	
	public static Long getAccountId(){
		Long id = null;
		Account account = getAccount();
		if(account != null){
			id = account.getId();
		}
		return id;
	}
}
