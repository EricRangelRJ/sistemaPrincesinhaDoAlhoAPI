package br.com.princesinhadoalho.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.princesinhadoalho.enums.Ativo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produtos")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProduto")
	private Integer idProduto;

	@Column(length = 50, nullable = false)
	private String nomeProduto;

	@Column(length = 15, nullable = false, unique = true)
	private String codigo;

	@Column(length = 255)
	private String descricao;

	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@Enumerated(EnumType.STRING)
	private Ativo ativo;
	
	private Double peso;

	private Double valorCusto;

	private Double valorVenda;

	private Double margemLucro;
	
	@ManyToOne
	@JoinColumn(name = "idFornecedor", nullable = false)
	private Fornecedor fornecedor;

	@OneToMany(mappedBy = "idItemPedido.produto") 
	private Set<ItemPedido> itens = new HashSet<>();
	
	public Produto(Integer idProduto, String nomeProduto, String codigo, String descricao, Date dataCadastro,
			Ativo ativo, Double peso, Double valorCusto, Double valorVenda, Double margemLucro, Fornecedor fornecedor) {
		super();
		this.idProduto = idProduto;
		this.nomeProduto = nomeProduto;
		this.codigo = codigo;
		this.descricao = descricao;
		this.dataCadastro = dataCadastro;
		this.ativo = ativo;
		this.peso = peso;
		this.valorCusto = valorCusto;
		this.valorVenda = valorVenda;
		this.margemLucro = margemLucro;
		this.fornecedor = fornecedor;
	}
	
	@JsonIgnore
	public Set<Pedido> getPedidos(){
		Set<Pedido> set = new HashSet<>();
		for(ItemPedido x : itens) {
			set.add(x.getPedido());
		}
		return set;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(fornecedor, other.fornecedor)
				&& Objects.equals(nomeProduto, other.nomeProduto) && Objects.equals(peso, other.peso);
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricao, fornecedor, nomeProduto, peso);
	}

	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", nomeProduto=" + nomeProduto + ", codigo=" + codigo
				+ ", fornecedor=" + fornecedor + "]";
	}
	
}
