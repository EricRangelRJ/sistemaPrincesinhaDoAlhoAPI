package br.com.princesinhadoalho.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.princesinhadoalho.dtos.usuarios.RecuperarSenhaPostDTO;
import br.com.princesinhadoalho.exceptions.ServiceException;
import br.com.princesinhadoalho.services.RecuperarSenhaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@Api(tags = "Recuperação de senha")
@RequestMapping(value = "/api/recuperarsenha")
public class RecuperarSenhaController {

	private final RecuperarSenhaService service;

	@PostMapping
	@ApiOperation(value = "recuperar")
	public ResponseEntity<String> recuperar(@RequestBody RecuperarSenhaPostDTO dto) {

		try {
			String response = service.recuperarSenha(dto);
			return ResponseEntity.ok(response);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

}
