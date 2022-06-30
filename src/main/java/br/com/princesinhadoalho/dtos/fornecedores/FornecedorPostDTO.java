package br.com.princesinhadoalho.dtos.fornecedores;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornecedorPostDTO {

	private String nome;
	private String cpfCnpj;
	private String telefone1;
	private String telefone2;
	private String email;
	private String logradouro;
	private String numero;
	private String complemento;
	private String condominio;
	private String bairro;	
	private String municipio;
	private String estado;
	private String cep;
	
}
