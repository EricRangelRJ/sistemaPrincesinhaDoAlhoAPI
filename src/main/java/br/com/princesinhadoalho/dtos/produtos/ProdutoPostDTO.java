package br.com.princesinhadoalho.dtos.produtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoPostDTO {

	@NotBlank(message = "{nome.not.blank}")
	private String nomeProduto;
//	private String codigo;    - GERADO ALEATORIAMENTE
	private String descricao;
//	private String dataCadastro; - OBTIDO DO SISTEMA 
	private Boolean ativo;
	private Double peso;
	private Double valorCusto;
	private Double valorVenda;
	
	@NotNull
	private Integer idFornecedor;

}
