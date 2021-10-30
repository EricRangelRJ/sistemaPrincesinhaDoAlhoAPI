package br.com.princesinhadoalho.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.princesinhadoalho.dtos.UsuarioPostDTO;
import br.com.princesinhadoalho.entities.Usuario;
import br.com.princesinhadoalho.repositories.UsuarioRepository;
import br.com.princesinhadoalho.security.Cryptography;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
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
}