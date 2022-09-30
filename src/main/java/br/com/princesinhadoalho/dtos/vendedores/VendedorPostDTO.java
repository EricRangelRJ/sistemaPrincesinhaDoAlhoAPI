package br.com.princesinhadoalho.dtos.vendedores;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class VendedorPostDTO {

	@NotBlank(message = "{nome.not.blank}")
	private String nome;
	
	private String apelido;
	
}
