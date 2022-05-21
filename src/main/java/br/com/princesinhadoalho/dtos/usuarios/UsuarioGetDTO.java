package br.com.princesinhadoalho.dtos.usuarios;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioGetDTO {
	
	private Integer idUsuario;
	private String nome;
	private String email;

}
