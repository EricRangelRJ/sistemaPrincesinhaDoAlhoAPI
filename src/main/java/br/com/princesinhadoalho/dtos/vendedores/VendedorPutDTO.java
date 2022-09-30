package br.com.princesinhadoalho.dtos.vendedores;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class VendedorPutDTO {
	
	@NotNull
	private Integer idVendedor;
	
	@NotBlank(message = "{nome.not.blank}")
	private String nome;
	
	private String apelido;
}
