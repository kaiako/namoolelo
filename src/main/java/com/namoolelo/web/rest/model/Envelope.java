package com.namoolelo.web.rest.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class Envelope implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4866725036641720752L;
	
	private Map<String, Object> meta;
	private Object data;
//	private Account user;
	private int code;
	private String errorMsg;
	
	public Envelope(){
		this.meta = new LinkedHashMap<>();
		this.code = HttpStatus.OK.value();
	}

	public Envelope(Object object){
		this.meta = new LinkedHashMap<>();
		this.data = object;
		this.code = HttpStatus.OK.value();
	}
	
	public Envelope(HttpStatus code, String errorMsg){
		this.code = code.value();
		this.errorMsg = errorMsg;
	}
	
	public void putMeta(String key, Object value){
		meta.put(key,value);
	}
	
	public Object getMeta(String key){
		return meta.get(key);
	}
	
//	public Account getUser(){
//		if(user == null){
//			setUser(new Account());
//		}
//		return user;
//	}
	
}
