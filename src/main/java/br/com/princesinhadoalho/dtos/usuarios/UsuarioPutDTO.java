package br.com.princesinhadoalho.dtos.usuarios;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class UsuarioPutDTO {
	
	@NotNull
	private Integer idUsuario;
	
	@NotBlank(message = "{nome.not.blank}")
	private String nome;
	//private String email; NÃO PERMITIDO ATUALIZAR EMAIL DO USUÁRIO
	
	@NotBlank(message = "{senha.not.blank}")
	private String senha;
	
}
