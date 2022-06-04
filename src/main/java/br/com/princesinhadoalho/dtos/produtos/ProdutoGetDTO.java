package br.com.princesinhadoalho.dtos.produtos;

import br.com.princesinhadoalho.dtos.fornecedores.FornecedorGetDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoGetDTO {

	private Integer idProduto;
	private String nome;
	private String codigo;
	private String descricao;
	private String dataCadastro;
	private Boolean ativo;
	private Double valorCusto;
	private Double valorVenda;
	private Double margemLucro;
	private FornecedorGetDTO fornecedor;

}
