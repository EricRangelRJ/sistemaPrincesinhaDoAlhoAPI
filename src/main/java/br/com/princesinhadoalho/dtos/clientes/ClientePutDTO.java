package br.com.princesinhadoalho.dtos.clientes;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ClientePutDTO {

	@NotNull
	private Integer idCliente;
	
	@NotBlank(message = "{nome.not.blank}")
	private String nome;

	//	private String cpf; NÃO PERMITIDO ALTERAR CPf
	
	@Setter
	private String dataNascimento;
	
	@Email(message = "{email.email}")
	private String email;
	
	private String observacao;

}
