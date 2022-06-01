package br.com.princesinhadoalho.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.princesinhadoalho.dtos.usuarios.UsuarioGetDTO;
import br.com.princesinhadoalho.dtos.usuarios.UsuarioPostDTO;
import br.com.princesinhadoalho.dtos.usuarios.UsuarioPutDTO;
import br.com.princesinhadoalho.exceptions.ServiceException;
import br.com.princesinhadoalho.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@Api(tags = "Menu Usuários")
@RequestMapping(value = "/api/usuarios")
public class UsuariosController {

	private final UsuarioService service;

	@PostMapping
	@ApiOperation(value = "Cadastrar usuário")
	public ResponseEntity<UsuarioGetDTO> cadastrar(@RequestBody UsuarioPostDTO dto) {

		try {
			UsuarioGetDTO getDto = service.cadastrar(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(getDto);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping
	@ApiOperation(value = "Buscar usuários cadastrados")
	public ResponseEntity<List<UsuarioGetDTO>> buscarTodos() {

		try {
			List<UsuarioGetDTO> lista = service.buscarTodos();
			return ResponseEntity.ok(lista);
		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping(value = "/{idUsuario}")
	@ApiOperation(value = "Buscar usuário por Id")
	public ResponseEntity<UsuarioGetDTO> buscarId(@PathVariable("idUsuario") Integer idUsuario) {

		try {
			UsuarioGetDTO getDto = service.buscarId(idUsuario);
			return ResponseEntity.ok(getDto);
		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	
	@PutMapping
	@ApiOperation(value = "Atualizar usuário")
	public ResponseEntity<UsuarioGetDTO> atualizar(@RequestBody UsuarioPutDTO dto) {

		try {
			UsuarioGetDTO getDto = service.atualizar(dto);
			return ResponseEntity.ok(getDto);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping(value = "/{idUsuario}")
	@ApiOperation(value = "Excluir usuário por Id")
	public ResponseEntity<String> excluir(@PathVariable("idUsuario") Integer idUsuario) {

		try {
			String response = service.excluir(idUsuario);
			return ResponseEntity.ok(response);
		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
}