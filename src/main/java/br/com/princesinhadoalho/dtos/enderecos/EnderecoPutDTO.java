package br.com.princesinhadoalho.dtos.enderecos;

import lombok.Getter;

@Getter
public class EnderecoPutDTO {

	private Integer idEndereco;
	private String logradouro;
	private String cep;
	private String numero;
	private String complemento;
	private String observacao;
	private boolean condominio; 
}
