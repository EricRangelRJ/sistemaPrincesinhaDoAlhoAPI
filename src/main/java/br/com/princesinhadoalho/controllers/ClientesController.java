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

import br.com.princesinhadoalho.dtos.clientes.ClienteGetDTO;
import br.com.princesinhadoalho.dtos.clientes.ClientePostDTO;
import br.com.princesinhadoalho.dtos.clientes.ClientePutDTO;
import br.com.princesinhadoalho.exceptions.ServiceException;
import br.com.princesinhadoalho.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@Api(tags = "Menu Clientes")
@RequestMapping(value = "/api/clientes")
public class ClientesController {

	private ClienteService service;

	@PostMapping
	@ApiOperation(value = "Cadastrar cliente")
	public ResponseEntity<ClienteGetDTO> cadastrar(@RequestBody ClientePostDTO dto) {

		try {
			ClienteGetDTO getDto = service.cadastrar(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(getDto);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping
	@ApiOperation(value = "Buscar clientes cadastrados")
	public ResponseEntity<List<ClienteGetDTO>> buscarClientes() {

		try {
			List<ClienteGetDTO> lista = service.buscarClientes();
			return ResponseEntity.status(HttpStatus.OK).body(lista);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{idCliente}")
	@ApiOperation(value = "Buscar cliente pelo Id")
	public ResponseEntity<ClienteGetDTO> buscarId(@PathVariable("idCliente") Integer idCliente) {

		try {
			ClienteGetDTO getDto = service.buscarId(idCliente);
			return ResponseEntity.ok(getDto);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping
	@ApiOperation(value = "Atualizar cliente")
	public ResponseEntity<ClienteGetDTO> put(@RequestBody ClientePutDTO dto) {

		try {
			ClienteGetDTO getDto = service.atualizar(dto);
			return ResponseEntity.ok(getDto);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{idCliente}")
	@ApiOperation(value = "Excluir cliente pelo Id")
	public ResponseEntity<String> excluir(@PathVariable Integer idCliente) {

		try {
			String response = service.excluir(idCliente);
			return ResponseEntity.ok(response);
			
		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

}
