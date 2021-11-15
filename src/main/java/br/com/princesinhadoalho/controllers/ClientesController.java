package br.com.princesinhadoalho.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.princesinhadoalho.dtos.clientes.ClienteGetDTO;
import br.com.princesinhadoalho.dtos.clientes.ClientePostDTO;
import br.com.princesinhadoalho.dtos.clientes.ClientePutDTO;
import br.com.princesinhadoalho.entities.Cliente;
import br.com.princesinhadoalho.helpers.DateHelper;
import br.com.princesinhadoalho.repositories.ClienteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Api(tags =  "Menu Clientes")
@RestController
@Transactional
@RequestMapping(value = "/api/clientes")
public class ClientesController {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@ApiOperation(value = "Cadastrar clientes")
	@CrossOrigin
	@PostMapping
	public ResponseEntity<String> cadastrar(@RequestBody ClientePostDTO dto) {

		try {

			if (clienteRepository.findByCpf(dto.getCpf()) != null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: Cpf já cadastrado!");

			} else {

				Cliente cliente = new Cliente();
				cliente.setNome(dto.getNome());
				cliente.setDataNascimento(DateHelper.toDate(dto.getDataNascimento()));
				cliente.setCpf(dto.getCpf());

				clienteRepository.save(cliente);

				return ResponseEntity.status(HttpStatus.OK).body("Cliente cadastrado com sucesso!");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}

	}

	@ApiOperation(value = "Listar clientes cadastrados")
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<ClienteGetDTO>> buscarClientes() {

		try {

			List<ClienteGetDTO> lista = new ArrayList<ClienteGetDTO>();

			for (Cliente cliente : clienteRepository.findAll()) {

				ClienteGetDTO dto = new ClienteGetDTO();

				dto.setIdCliente(cliente.getIdCliente());
				dto.setNome(cliente.getNome());
				dto.setDataNascimento(DateHelper.toString(cliente.getDataNascimento()));
				dto.setCpf(cliente.getCpf());

				lista.add(dto);

			}

			return ResponseEntity.status(HttpStatus.OK).body(lista);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@ApiOperation(value = "Buscar clientes pelo Id")
	@CrossOrigin
	@GetMapping("/{idCliente}")
	public ResponseEntity<ClienteGetDTO> buscarId(@PathVariable Integer idCliente) {

		try {

			Optional<Cliente> cliente = clienteRepository.findById(idCliente);

			if (cliente == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

			} else {

				ClienteGetDTO dto = new ClienteGetDTO();

				dto.setIdCliente(cliente.get().getIdCliente());
				dto.setNome(cliente.get().getNome());
				dto.setDataNascimento(DateHelper.toString(cliente.get().getDataNascimento()));
				dto.setCpf(cliente.get().getCpf());

				return ResponseEntity.status(HttpStatus.OK).body(dto);
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}
	
	@ApiOperation(value = "Atualizar cliente")
	@CrossOrigin
	@PutMapping
	public ResponseEntity<String> put(@RequestBody ClientePutDTO dto) {

		try {

			Optional<Cliente> result = clienteRepository.findById(dto.getIdCliente());

			if (result == null || result.isEmpty()) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Cliente não encontrado.");
			}

			Cliente cliente = result.get();
			cliente.setNome(dto.getNome());
			
			clienteRepository.save(cliente);
			
			return ResponseEntity.status(HttpStatus.OK).body("Cliente atualizado com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}
	}
	
	@ApiOperation(value = "Excluir cliente")
	@CrossOrigin
	@DeleteMapping ("/{idCliente}")
	public ResponseEntity<String> excluir(@PathVariable Integer idCliente) {

		try {

			Optional<Cliente> result = clienteRepository.findById(idCliente);

			if (result == null || result.isEmpty()) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Cliente não encontrado!");
			} else {

				Cliente cliente = result.get();
				clienteRepository.delete(cliente);

				return ResponseEntity.status(HttpStatus.OK).body("Cliente excluído com sucesso!");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}
	}

}
