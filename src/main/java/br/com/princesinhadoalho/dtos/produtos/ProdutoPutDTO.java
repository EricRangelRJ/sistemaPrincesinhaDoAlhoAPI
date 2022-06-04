package br.com.princesinhadoalho.dtos.produtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoPutDTO {

	private Integer idProduto;
	private String nome;
//	private String codigo; Não permitido
	private String descricao;
	private String dataCadastro;
	private Boolean ativo;
	private Double valorCusto;
	private Double valorVenda;
	private Double margemLucro;
}
