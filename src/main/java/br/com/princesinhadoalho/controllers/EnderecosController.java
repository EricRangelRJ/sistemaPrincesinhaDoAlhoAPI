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

import br.com.princesinhadoalho.dtos.enderecos.EnderecoGetDTO;
import br.com.princesinhadoalho.dtos.enderecos.EnderecoPostDTO;
import br.com.princesinhadoalho.dtos.enderecos.EnderecoPutDTO;
import br.com.princesinhadoalho.exceptions.ServiceException;
import br.com.princesinhadoalho.services.EnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@Api(tags = "Menu Enderecos")
@RequestMapping(value = "/api/enderecos")
public class EnderecosController {

	private final EnderecoService service;

	@PostMapping
	@ApiOperation(value = "cadastrar")
	public ResponseEntity<EnderecoGetDTO> cadastrar(@RequestBody EnderecoPostDTO dto) {

		try {
			EnderecoGetDTO getDto = service.cadastrar(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(getDto);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping
	@ApiOperation(value = "Buscar endereços")
	public ResponseEntity<List<EnderecoGetDTO>> buscarEnderecos() {

		try {
			List<EnderecoGetDTO> lista = service.buscarEnderecos();
			return ResponseEntity.ok(lista);
		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping(value = "/{idEndereco}")
	@ApiOperation(value = "Buscar por Id")
	public ResponseEntity<EnderecoGetDTO> buscarId(@PathVariable("idEndereco") Integer idEndereco) {

		try {
			EnderecoGetDTO getDto = service.buscarId(idEndereco);
			return ResponseEntity.ok(getDto);
		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	
	@PutMapping
	@ApiOperation(value = "atualizar")
	public ResponseEntity<String> atualizar(@RequestBody EnderecoPutDTO dto) {

		try {
			String response = service.atualizar(dto);
			return ResponseEntity.ok(response);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping(value = "/{idEndereco}")
	@ApiOperation(value = "Excluir")
	public ResponseEntity<String> excluir(@PathVariable("idEndereco") Integer idEndereco) {

		try {
			String response = service.excluir(idEndereco);
			return ResponseEntity.ok(response);
		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
}