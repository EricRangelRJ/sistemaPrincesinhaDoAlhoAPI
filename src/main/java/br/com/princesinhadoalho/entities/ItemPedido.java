package br.com.princesinhadoalho.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.princesinhadoalho.entities.pk.ItemPedidoPk;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "itemPedido")
public class ItemPedido implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ItemPedidoPk idItemPedido = new ItemPedidoPk();
	
	private Integer quantidade;
	private Double preco;
		
	public ItemPedido(Pedido pedido, Produto produto, Integer quantidade, Double preco) {
		idItemPedido.setPedido(pedido);
		idItemPedido.setProduto(produto);
		this.quantidade = quantidade;
		this.preco = preco;
		
	}

	public Pedido getPedido() {
		return idItemPedido.getPedido();
	}
	
	public void setPedido(Pedido pedido) {
		idItemPedido.setPedido(pedido);
	}
	
	public Produto getProduto() {
		return idItemPedido.getProduto();
	}
	
	public void setProduto(Produto produto) {
		idItemPedido.setProduto(produto);
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Double getSubTotal() {
		return quantidade * preco;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idItemPedido);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(idItemPedido, other.idItemPedido);
	}
	
	@Override
	public String toString() {
		return "ItemPedido [idItemPedido=" + idItemPedido + ", quantidade=" + quantidade + ", preco=" + preco + "]";
	}

		
}
