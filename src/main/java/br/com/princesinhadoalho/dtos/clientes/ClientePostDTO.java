package br.com.princesinhadoalho.dtos.clientes;

import lombok.Getter;

@Getter
public class ClientePostDTO {

	private String nome;
	private String dataNascimento;
	private String cpf;
	private Integer idEndereco;	
	
}
