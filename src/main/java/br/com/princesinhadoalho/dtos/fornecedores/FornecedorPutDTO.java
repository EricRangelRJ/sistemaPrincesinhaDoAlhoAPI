package br.com.princesinhadoalho.dtos.fornecedores;

import lombok.Getter;

@Getter
public class FornecedorPutDTO {

	private Integer idFornecedor;
//	private String cpfCnpj; Não permitido
	private String nome;

}
