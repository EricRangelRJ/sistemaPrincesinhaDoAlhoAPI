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

import br.com.princesinhadoalho.dtos.clientes.TipoLogradouroDTO;
import br.com.princesinhadoalho.dtos.clientes.TipoLogradouroPutDTO;
import br.com.princesinhadoalho.exceptions.ServiceException;
import br.com.princesinhadoalho.services.TipoLogradouroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@Api(tags = "Menu Tipo Logradouro")
@RequestMapping(value = "/api/tipo_logradouro")
public class TipoLogradouroController {

	private final TipoLogradouroService service;

	@PostMapping
	@ApiOperation(value = "Cadastrar tipo logradouro")
	public ResponseEntity<TipoLogradouroDTO> cadastrar(@Valid @RequestBody TipoLogradouroDTO dto) {

		try {
			TipoLogradouroDTO getDto = service.cadastrar(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(getDto);

		} catch (ServiceException | IllegalArgumentException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping
	@ApiOperation(value = "Buscar tipos de logradouros cadastrados")
	public ResponseEntity<List<TipoLogradouroDTO>> buscarTipoLogradouros() {

		try {
			List<TipoLogradouroDTO> lista = service.buscarTipoLogradouro();
			return ResponseEntity.status(HttpStatus.OK).body(lista);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{idTipoLogradouro}")
	@ApiOperation(value = "Buscar tipo logradouro pelo Id")
	public ResponseEntity<TipoLogradouroDTO> buscarId(@PathVariable("idTipoLogradouro") Integer idTipoLogradouro) {

		try {
			TipoLogradouroDTO getDto = service.buscarId(idTipoLogradouro);
			return ResponseEntity.ok(getDto);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping
	@ApiOperation(value = "Atualizar tipo logradouro")
	public ResponseEntity<TipoLogradouroDTO> atualizar(@Valid @RequestBody TipoLogradouroPutDTO dto) {

		try {
			TipoLogradouroDTO getDto = service.atualizar(dto);
			return ResponseEntity.ok(getDto);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{idTipoLogradouro}")
	@ApiOperation(value = "Excluir tipo logradouro pelo Id")
	public ResponseEntity<String> excluir(@PathVariable Integer idTipoLogradouro) throws Exception {

		try {
			String response = service.excluir(idTipoLogradouro);
			return ResponseEntity.ok(response);
			
		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

}
