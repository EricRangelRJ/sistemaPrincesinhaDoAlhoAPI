package br.com.princesinhadoalho.dtos.produtos;

import br.com.princesinhadoalho.dtos.fornecedores.FornecedorGetDTO;
import br.com.princesinhadoalho.entities.Produto;
import br.com.princesinhadoalho.helpers.DateHelper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoGetDTO {

	private Integer idProduto;
	private String nomeProduto;
	private String codigo;
	private String descricao;
	private String dataCadastro;
	private String ativo;
	private Double peso;
	private Double valorCusto;
	private Double valorVenda;
	private Double margemLucro;
	
	private FornecedorGetDTO fornecedor;
	
	public ProdutoGetDTO(Produto produto) {
		this.idProduto = produto.getIdProduto();
		this.nomeProduto = produto.getNomeProduto();
		this.codigo = produto.getCodigo();
		this.descricao = produto.getDescricao();
		this.dataCadastro = DateHelper.toString(produto.getDataCadastro());
		this.ativo =  produto.getAtivo();
		this.peso = produto.getPeso();
		this.valorCusto = produto.getValorCusto();
		this.valorVenda = produto.getValorVenda();
		this.margemLucro = produto.getMargemLucro();
		this.fornecedor = new FornecedorGetDTO(produto.getFornecedor());
	}
	
}
