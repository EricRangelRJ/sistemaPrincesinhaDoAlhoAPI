package br.com.princesinhadoalho.dtos.clientes;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.princesinhadoalho.dtos.enderecos.EnderecoPostDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientePostDTO {

	@NotBlank(message = "{nome.not.blank}")
	private String nome;
	
	//@NotBlank(message = "{cpf.not.blank}")
	//@Size(min= 11, max = 11, message = "{cpf.size}")
	//@Pattern(regexp = "^\\d+$", message = "{number.pattern}")
	private String cpf;
	
	private String dataNascimento;
	
	@Email(message = "{email.email}")
	private String email;
	
	private String observacao;
	
	private EnderecoPostDTO endereco;
	
}


