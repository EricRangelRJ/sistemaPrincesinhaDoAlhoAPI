package br.com.princesinhadoalho.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.princesinhadoalho.dtos.usuarios.UsuarioGetDTO;
import br.com.princesinhadoalho.dtos.usuarios.UsuarioPostDTO;
import br.com.princesinhadoalho.entities.Usuario;
import br.com.princesinhadoalho.repositories.UsuarioRepository;
import br.com.princesinhadoalho.security.Cryptography;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

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

				return ResponseEntity.status(HttpStatus.OK).body("Usuário cadastrado com sucesso");

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

}