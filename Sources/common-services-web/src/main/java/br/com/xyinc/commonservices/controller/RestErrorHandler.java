package br.com.xyinc.commonservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/***
 * 
 * @author Danilo Nogueira Costa
 *
 */
@ControllerAdvice
public class RestErrorHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestErrorHandler.class);

	@ExceptionHandler(AddressException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleTodoNotFoundException(AddressException e) {
		LOGGER.error("Erro ao tentar buscar endereço", e);
	}

}
