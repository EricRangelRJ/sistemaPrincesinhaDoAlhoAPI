package br.com.princesinhadoalho.dtos.produtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoPutDTO {

	private Integer idProduto;
	private String nomeProduto;
//  private String codigo;  - NÃO PERMITIDO ALTERAR
	private String descricao;
//	private String dataCadastro; - NÃO PERMITIDO ALTERAR
	private Boolean ativo;
	private Double peso;
	private Double valorCusto;
	private Double valorVenda;
	
	private Integer idFornecedor;

}
