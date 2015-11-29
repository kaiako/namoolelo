package com.namoolelo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="account")
public class Account implements Identifiable<Long>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5950302494038930135L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String username;
	@Column(unique=true)
	private String email;
	private String password;
}
