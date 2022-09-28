package br.com.princesinhadoalho.dtos.produtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoPostDTO {

	private String nomeProduto;
//	private String codigo;    - GERADO ALEATORIAMENTE
	private String descricao;
//	private String dataCadastro; - OBTIDO DO SISTEMA 
	private Boolean ativo;
	private Double peso;
	private Double valorCusto;
	private Double valorVenda;
	
	private Integer idFornecedor;

}
