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

import br.com.princesinhadoalho.dtos.clientes.TipoEnderecoDTO;
import br.com.princesinhadoalho.dtos.clientes.TipoEnderecoPutDTO;
import br.com.princesinhadoalho.exceptions.ServiceException;
import br.com.princesinhadoalho.services.TipoEnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@Api(tags = "Menu Tipo Endereco")
@RequestMapping(value = "/api/tipo_Endereco")
public class TipoEnderecoController {

	private final TipoEnderecoService service;

	@PostMapping
	@ApiOperation(value = "Cadastrar tipo Endereco")
	public ResponseEntity<TipoEnderecoDTO> cadastrar(@Valid @RequestBody TipoEnderecoDTO dto) {

		try {
			TipoEnderecoDTO getDto = service.cadastrar(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(getDto);

		} catch (ServiceException | IllegalArgumentException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping
	@ApiOperation(value = "Buscar tipos de Enderecos cadastrados")
	public ResponseEntity<List<TipoEnderecoDTO>> buscarTipoEnderecos() {

		try {
			List<TipoEnderecoDTO> lista = service.buscarTipoEndereco();
			return ResponseEntity.status(HttpStatus.OK).body(lista);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{idTipoEndereco}")
	@ApiOperation(value = "Buscar tipo Endereco pelo Id")
	public ResponseEntity<TipoEnderecoDTO> buscarId(@PathVariable("idTipoEndereco") Integer idTipoEndereco) {

		try {
			TipoEnderecoDTO getDto = service.buscarId(idTipoEndereco);
			return ResponseEntity.ok(getDto);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping
	@ApiOperation(value = "Atualizar tipo Endereco")
	public ResponseEntity<TipoEnderecoDTO> atualizar(@Valid @RequestBody TipoEnderecoPutDTO dto) {

		try {
			TipoEnderecoDTO getDto = service.atualizar(dto);
			return ResponseEntity.ok(getDto);

		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{idTipoEndereco}")
	@ApiOperation(value = "Excluir tipo Endereco pelo Id")
	public ResponseEntity<String> excluir(@PathVariable Integer idTipoEndereco) throws Exception {

		try {
			String response = service.excluir(idTipoEndereco);
			return ResponseEntity.ok(response);
			
		} catch (ServiceException e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

}
