package br.com.princesinhadoalho.dtos.usuarios;

import br.com.princesinhadoalho.entities.Usuario;
import br.com.princesinhadoalho.security.TokenSecurity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthGetDTO {
	
	private Integer idUsuario;
	private String nome;
	private String email;
	private String accessToken;
	
	
	public AuthGetDTO(Usuario usuario) {
		this.idUsuario = usuario.getIdUsuario();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		// Gerando o token do para o Usuário
		this.accessToken = TokenSecurity.generateToken(usuario.getEmail());
	}	
	
	
	
}
