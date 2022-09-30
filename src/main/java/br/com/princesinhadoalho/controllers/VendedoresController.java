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

import br.com.princesinhadoalho.dtos.vendedores.VendedorGetDTO;
import br.com.princesinhadoalho.dtos.vendedores.VendedorPostDTO;
import br.com.princesinhadoalho.dtos.vendedores.VendedorPutDTO;
import br.com.princesinhadoalho.exceptions.ServiceException;
import br.com.princesinhadoalho.services.VendedorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;


@CrossOrigin
@RestController
@AllArgsConstructor
@Api(tags = "Menu Vendedores")
@RequestMapping(value = "/api/vendedores")
public class VendedoresController {

	private final VendedorService service;

	@PostMapping
	@ApiOperation(value = "Cadastrar vendedor")
	public ResponseEntity<VendedorGetDTO> cadastrar(@Valid @RequestBody VendedorPostDTO dto) {

		try {
			VendedorGetDTO getDto = service.cadastrar(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(getDto);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping
	@ApiOperation(value = "Buscar vendedores cadastrados")
	public ResponseEntity<List<VendedorGetDTO>> buscarVendedors() {

		try {
			List<VendedorGetDTO> lista = service.buscarVendedors();
			return ResponseEntity.status(HttpStatus.OK).body(lista);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{idVendedor}")
	@ApiOperation(value = "Buscar vendedor pelo Id")
	public ResponseEntity<VendedorGetDTO> buscarId(@PathVariable("idVendedor") Integer idVendedor) {

		try {
			VendedorGetDTO getDto = service.buscarId(idVendedor);
			return ResponseEntity.ok(getDto);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping
	@ApiOperation(value = "Atualizar vendedor")
	public ResponseEntity<VendedorGetDTO> atualizar(@Valid @RequestBody VendedorPutDTO dto) {

		try {
			VendedorGetDTO getDto = service.atualizar(dto);
			return ResponseEntity.ok(getDto);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{idVendedor}")
	@ApiOperation(value = "Excluir vendedor pelo Id")
	public ResponseEntity<String> excluir(@PathVariable Integer idVendedor) {

		try {
			String response = service.excluir(idVendedor);
			return ResponseEntity.ok(response);
			
		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

}
