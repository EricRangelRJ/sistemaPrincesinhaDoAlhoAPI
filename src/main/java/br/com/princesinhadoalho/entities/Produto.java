package br.com.princesinhadoalho.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produto")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProduto")
	private Integer idProduto;

	@Column(length = 150, nullable = false)
	private String descricao;

	@Column(length = 20)
	private Double valorCusto;

	@Column(length = 20)
	private Double valorVenda;

	@Column(length = 1)
	private Boolean ativo;

	@Column(length = 10)
	private Double margemLucro;

	@Column(length = 15)
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@ManyToOne
	@JoinColumn(name = "idFornecedor", nullable = false)
	private Fornecedor fornecedor;
	
	@OneToMany(mappedBy = "produto")
	private Set<ItemPedido> itensPedido = new HashSet<>();

}
