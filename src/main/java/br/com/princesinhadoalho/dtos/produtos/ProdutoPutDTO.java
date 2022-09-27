package br.com.princesinhadoalho.dtos.produtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoPutDTO {

	private Integer idProduto;
	private String nomeProduto;
//  private String codigo;
	private String descricao;
//	private String dataCadastro;
	private String ativo;
	private Double peso;
	private Double valorCusto;
	private Double valorVenda;
	//private Double margemLucro;
	
	private Integer idFornecedor;

}
