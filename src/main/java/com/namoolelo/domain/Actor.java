package com.namoolelo.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.util.Assert;

import lombok.Data;

@Data
@Entity
@Table(name="actor")
public class Actor implements Identifiable<Long>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3370037581952550275L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String name;
	private String description;
	@ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="moolelo_actor", 
	    joinColumns=@JoinColumn(name="moolelo_id"), 
	    inverseJoinColumns=@JoinColumn(name="actor_id")) 
	private Set<Moolelo> moolelos;
	
	public Set<Moolelo> getMoolelos(){
		if(moolelos == null){
			moolelos = new HashSet<Moolelo>();
		}
		return moolelos;
	}

	public void addMoolelo(Moolelo moolelo) {
		Assert.notNull(moolelo);
		getMoolelos().add(moolelo);		
	}
}
