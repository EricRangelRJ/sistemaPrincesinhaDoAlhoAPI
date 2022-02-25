package br.com.princesinhadoalho.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.princesinhadoalho.dtos.usuarios.AuthGetDTO;
import br.com.princesinhadoalho.dtos.usuarios.AuthPostDTO;
import br.com.princesinhadoalho.entities.Usuario;
import br.com.princesinhadoalho.repositories.UsuarioRepository;
import br.com.princesinhadoalho.security.Cryptography;
import br.com.princesinhadoalho.security.TokenSecurity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

<<<<<<< Updated upstream
@Api(tags =  "Login")
=======
>>>>>>> Stashed changes
@RestController
@Transactional
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@ApiOperation(value = "Autenticação de usuário")
	@CrossOrigin
	@PostMapping
	public ResponseEntity<AuthGetDTO> post(@RequestBody AuthPostDTO dto) {

		try {

			Usuario usuario = usuarioRepository.findByEmailAndSenha(dto.getEmail(),
					Cryptography.encrypt(dto.getSenha()));

			if (usuario != null) {

				AuthGetDTO dtoGet = new AuthGetDTO();

				dtoGet.setIdUsuario(usuario.getIdUsuario());
				dtoGet.setNome(usuario.getNome());
				dtoGet.setEmail(usuario.getEmail());
				dtoGet.setAccessToken(TokenSecurity.generateToken(usuario.getEmail()));

				return ResponseEntity.status(HttpStatus.OK).body(dtoGet);

			} else {

				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}
}