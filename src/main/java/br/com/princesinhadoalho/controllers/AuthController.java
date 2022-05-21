package br.com.princesinhadoalho.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.princesinhadoalho.dtos.usuarios.AuthGetDTO;
import br.com.princesinhadoalho.dtos.usuarios.AuthPostDTO;
import br.com.princesinhadoalho.exceptions.ServiceException;
import br.com.princesinhadoalho.services.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@Api(tags = "Login")
@RequestMapping(value = "/api/auth")
public class AuthController {

	private final AuthService service;

	@PostMapping
	@ApiOperation(value = "autenticar")
	public ResponseEntity<AuthGetDTO> autenticar(@RequestBody AuthPostDTO dto) {

		try {
			AuthGetDTO getDto = service.autenticar(dto);
			return ResponseEntity.ok(getDto);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}