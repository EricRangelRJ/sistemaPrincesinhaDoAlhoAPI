package br.com.princesinhadoalho.dtos.usuarios;

import lombok.Getter;

@Getter
public class UsuarioPutDTO {
	
	private Integer idUsuario;
	private String nome;
	//private String email; NÃO PERMITIDO
	private String senha;
	
}
