package br.com.xyinc.commonservices.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/***
 * 
 * @author Danilo Nogueira Costa
 *
 */

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Nenhum endereço encontrado")
public class AddressException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6050416442027751497L;

	public AddressException(String zipCodeValue) {
		super(zipCodeValue);
	}
}
