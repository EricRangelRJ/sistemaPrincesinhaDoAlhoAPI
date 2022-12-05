package br.com.princesinhadoalho.exceptions;

import java.io.Serializable;

public class ConstraintViolationException extends RuntimeException implements Serializable{
	private static final long serialVersionUID = 1L;

	public ConstraintViolationException(String msg){
		super(msg);
	}
}
