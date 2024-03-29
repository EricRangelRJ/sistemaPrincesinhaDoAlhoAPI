package br.com.princesinhadoalho.controllers;

import java.util.List;

import javax.validation.Valid;

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

import br.com.princesinhadoalho.dtos.fornecedores.FornecedorGetDTO;
import br.com.princesinhadoalho.dtos.fornecedores.FornecedorPostDTO;
import br.com.princesinhadoalho.dtos.fornecedores.FornecedorPutDTO;
import br.com.princesinhadoalho.exceptions.ServiceException;
import br.com.princesinhadoalho.services.FornecedorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@Api(tags = "Menu Fornecedores")
@RequestMapping(value = "/api/fornecedores")
public class FornecedoresController {

	private final FornecedorService service;

	@PostMapping
	@ApiOperation(value = "Cadastrar fornecedor")
	public ResponseEntity<FornecedorGetDTO> cadastrar(@Valid @RequestBody FornecedorPostDTO dto) {

		try {
			FornecedorGetDTO getDto = service.cadastrar(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(getDto);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping
	@ApiOperation(value = "Buscar fornecedores cadastrados")
	public ResponseEntity<List<FornecedorGetDTO>> buscarFornecedores() {

		try {
			List<FornecedorGetDTO> lista = service.buscarFornecedores();
			return ResponseEntity.status(HttpStatus.OK).body(lista);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/{idFornecedor}")
	@ApiOperation(value = "Buscar fornecedor pelo Id")
	public ResponseEntity<FornecedorGetDTO> buscarId(@PathVariable("idFornecedor") Integer idFornecedor) {

		try {
			FornecedorGetDTO getDto = service.buscarId(idFornecedor);
			return ResponseEntity.ok(getDto);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping
	@ApiOperation(value = "Atualizar fornecedor")
	public ResponseEntity<FornecedorGetDTO> atualizar(@Valid @RequestBody FornecedorPutDTO dto) {

		try {
			FornecedorGetDTO getDto = service.atualizar(dto);
			return ResponseEntity.ok(getDto);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{idFornecedor}")
	@ApiOperation(value = "Excluir fornecedor pelo Id")
	public ResponseEntity<String> excluir(@PathVariable Integer idFornecedor) {

		try {
			String response = service.excluir(idFornecedor);
			return ResponseEntity.ok(response);
			
		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

}
