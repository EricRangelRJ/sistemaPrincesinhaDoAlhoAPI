package br.com.princesinhadoalho.dtos.clientes;

import lombok.Getter;

@Getter
public class ClientePutDTO {

	private Integer idCliente;
	private String nome;
//	private String cpf; NÃO PERMITIDO ALTERAR CPf
	private String dataNascimento;
	private String telefone1;
	private String telefone2;
	private String email;
	private String observacao;
	private String logradouro;
	private String numero;
	private String complemento;
	private String condominio;
	private String bairro;	
	private String municipio;
	private String estado;
	private String cep;	

}
