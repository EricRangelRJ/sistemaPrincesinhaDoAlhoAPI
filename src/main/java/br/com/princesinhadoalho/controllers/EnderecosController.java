package br.com.princesinhadoalho.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.princesinhadoalho.dtos.enderecos.EnderecoDTO;
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

	@GetMapping
	@ApiOperation(value = "Buscar endereços cadastrados")
	public ResponseEntity<List<EnderecoDTO>> buscarEnderecos() {

		try {
			List<EnderecoDTO> lista = service.buscarEnderecos();
			return ResponseEntity.ok(lista);
		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping(value = "/{idEndereco}")
	@ApiOperation(value = "Buscar endereço pelo Id")
	public ResponseEntity<EnderecoDTO> buscarId(@PathVariable("idEndereco") Integer idEndereco) {

		try {
			EnderecoDTO getDto = service.buscarId(idEndereco);
			return ResponseEntity.ok(getDto);
		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}	
	
	@DeleteMapping(value = "/{idEndereco}")
	@ApiOperation(value = "Excluir endereço pelo Id")
	public ResponseEntity<String> excluir(@PathVariable("idEndereco") Integer idEndereco){
		
		try {
			String response = service.excluir(idEndereco);
			return ResponseEntity.ok(response);
		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

}