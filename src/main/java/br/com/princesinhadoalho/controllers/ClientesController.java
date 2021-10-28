package br.com.princesinhadoalho.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClientesController {

	// atributo para armazenar o endereço do serviço
	private static final String ENDPOINT = "/api/clientes";

	// método para cadastrar cliente
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	public ResponseEntity<String> post() {

		return ResponseEntity.status(HttpStatus.OK).body("Cadastro de fornecedor executado com sucesso!");
	}

}
