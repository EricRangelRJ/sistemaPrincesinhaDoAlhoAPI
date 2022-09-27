package br.com.princesinhadoalho.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.usuarios.AuthGetDTO;
import br.com.princesinhadoalho.dtos.usuarios.AuthPostDTO;
import br.com.princesinhadoalho.entities.Usuario;
import br.com.princesinhadoalho.exceptions.BadRequestException;
import br.com.princesinhadoalho.repositories.UsuarioRepository;
import br.com.princesinhadoalho.security.Cryptography;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {

	private final UsuarioRepository repository;
	
	public AuthGetDTO autenticar(AuthPostDTO dto) {
		
		// Criptografando a senha do usuário
		String senha = Cryptography.encrypt(dto.getSenha());

		// Buscando no banco um usuário, pelo email e pela senha já criptografada
		Optional<Usuario> result = repository.findByEmailAndSenha(dto.getEmail(), senha);
		
		if(result.isEmpty()) {
			throw new BadRequestException("Email ou senha inválidos!");
		}
		
		Usuario usuario = result.get();
		
		return new AuthGetDTO(usuario);
		
	}
}
