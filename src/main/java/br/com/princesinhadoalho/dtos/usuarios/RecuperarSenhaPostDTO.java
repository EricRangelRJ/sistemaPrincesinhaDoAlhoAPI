package br.com.princesinhadoalho.dtos.usuarios;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class RecuperarSenhaPostDTO {

	@NotBlank(message = "{email.not.blank}")
	@Email(message = "{email.email}")
	private String email;
	
}
