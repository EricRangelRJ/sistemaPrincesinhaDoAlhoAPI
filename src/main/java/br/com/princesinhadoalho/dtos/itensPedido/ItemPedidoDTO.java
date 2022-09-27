package br.com.princesinhadoalho.dtos.itensPedido;

import br.com.princesinhadoalho.dtos.produtos.ProdutoGetDTO;
import br.com.princesinhadoalho.entities.ItemPedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemPedidoDTO {
	
	private Integer quantidade;
	private Double preco;
	private Double subTotal;
	private ProdutoGetDTO produto;
	
	// Convertendo um ItemPedido em Dto via construtor
	public ItemPedidoDTO(ItemPedido item) {
		this.quantidade = item.getQuantidade();
		this.preco = item.getPreco();
		this.subTotal = item.getSubTotal();
		this.produto = new ProdutoGetDTO(item.getProduto());
	}

}
