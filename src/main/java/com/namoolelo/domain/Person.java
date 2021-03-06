package com.namoolelo.domain;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="person")
public class Person  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5410024374910586245L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Embedded
	private Name name;
}
