package br.com.princesinhadoalho.dtos.produtos;

import lombok.Getter;

@Getter
public class ProdutoPostDTO {

	private String nome;
	private String codigo;
	private String descricao;
	private String dataCadastro;
	private Boolean ativo;
	private Double valorCusto;
	private Double valorVenda;
	private Double margemLucro;
	private Integer idFornecedor;

}
