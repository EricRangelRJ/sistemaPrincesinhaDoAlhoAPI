package br.com.princesinhadoalho.exceptions;

import java.io.Serializable;

public class EntityNotFoundException extends RuntimeException implements Serializable{
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String msg){
		super(msg);
	}
}
