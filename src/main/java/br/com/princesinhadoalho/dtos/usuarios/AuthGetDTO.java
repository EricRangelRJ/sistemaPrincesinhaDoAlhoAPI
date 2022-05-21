package br.com.princesinhadoalho.dtos.usuarios;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthGetDTO {
	
	private Integer idUsuario;
	private String nome;
	private String email;
	private String accessToken;	
	
}
