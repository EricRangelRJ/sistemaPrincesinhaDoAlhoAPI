package br.com.princesinhadoalho.dtos.usuarios;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class UsuarioPostDTO {
	
	@NotBlank(message = "{nome.not.blank}")
	private String nome;
	
	@NotBlank(message = "{email.not.blank}")
	@Email(message = "{email.email}")
	private String email;
	
	@NotBlank(message = "{senha.not.blank}")
	private String senha;
	
}
