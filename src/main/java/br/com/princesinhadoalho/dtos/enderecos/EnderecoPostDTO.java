package br.com.princesinhadoalho.dtos.enderecos;

import lombok.Getter;

@Getter
public class EnderecoPostDTO {

	private String logradouro;
	private String cep;
	private String numero;
	private String complemento;
	private String observacao;
	private String nomeCondominio;

}
