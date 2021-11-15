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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.princesinhadoalho.dtos.usuarios.UsuarioGetDTO;
import br.com.princesinhadoalho.dtos.usuarios.UsuarioPostDTO;
import br.com.princesinhadoalho.dtos.usuarios.UsuarioPutDTO;
import br.com.princesinhadoalho.entities.Usuario;
import br.com.princesinhadoalho.repositories.UsuarioRepository;
import br.com.princesinhadoalho.security.Cryptography;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Api(tags = "Menu Usuários")
@RestController
@Transactional
@RequestMapping(value = "/api/usuarios")
public class UsuariosController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@ApiOperation(value = "Cadastrar usuários")
	@CrossOrigin
	@PostMapping
	public ResponseEntity<String> cadastrar(@RequestBody UsuarioPostDTO dto) {

		try {

			if (usuarioRepository.findByEmail(dto.getEmail()) != null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("E-mail já cadastrado!");

			} else {
				Usuario usuario = new Usuario();
				usuario.setNome(dto.getNome());
				usuario.setEmail(dto.getEmail());
				usuario.setSenha(Cryptography.encrypt(dto.getSenha()));

				usuarioRepository.save(usuario);

				return ResponseEntity.status(HttpStatus.OK).body("Usuário cadastrado com sucesso!");

			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}
	}

	@ApiOperation(value = "Listar usuários cadastrados")
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<UsuarioGetDTO>> buscarUsuarios() {

		try {

			List<UsuarioGetDTO> lista = new ArrayList<UsuarioGetDTO>();

			for (Usuario usuario : usuarioRepository.findAll()) {

				UsuarioGetDTO dto = new UsuarioGetDTO();
				dto.setIdUsuario(usuario.getIdUsuario());
				dto.setNome(usuario.getNome());
				dto.setEmail(usuario.getEmail());

				lista.add(dto);

			}

			return ResponseEntity.status(HttpStatus.OK).body(lista);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@ApiOperation(value = "Buscar usuário pelo Id")
	@CrossOrigin
	@RequestMapping(value = "/{idUsuario}", method = RequestMethod.GET)
	public ResponseEntity<UsuarioGetDTO> buscarUsuario(@PathVariable Integer idUsuario) {

		try {

			Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

			if (usuario == null || usuario.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

			} else {

				UsuarioGetDTO dto = new UsuarioGetDTO();
				dto.setIdUsuario(usuario.get().getIdUsuario());
				dto.setNome(usuario.get().getNome());
				dto.setEmail(usuario.get().getEmail());

				return ResponseEntity.status(HttpStatus.OK).body(dto);
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@ApiOperation(value = "Atualizar usuário")
	@CrossOrigin
	@PutMapping("/")
	public ResponseEntity<String> atualizar(@RequestBody UsuarioPutDTO dto) {

		try {

			Optional<Usuario> result = usuarioRepository.findById(dto.getIdUsuario());

			if(result == null || result.isEmpty()) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Usuário não encontrado.");
				
			} else {

				Usuario usuario = result.get();
				usuario.setNome(dto.getNome());

				usuarioRepository.save(usuario);

				return ResponseEntity.status(HttpStatus.OK).body("Usuário atualizado com sucesso!");

			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}
	}

	@ApiOperation(value = "Excluir usuário")
	@CrossOrigin
	@DeleteMapping("/{idUsuario}")
	public ResponseEntity<String> excluir(@PathVariable Integer idUsuario) {

		try {

			Optional<Usuario> result = usuarioRepository.findById(idUsuario);

			if(result == null || result.isEmpty()) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Usuário não encontrado!");
			} else {

				Usuario usuario = result.get();
				usuarioRepository.delete(usuario);

				return ResponseEntity.status(HttpStatus.OK).body("Usuário excluído com sucesso!");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}
	}
}