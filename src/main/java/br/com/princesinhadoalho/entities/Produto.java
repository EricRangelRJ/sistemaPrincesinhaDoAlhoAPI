package br.com.princesinhadoalho.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.princesinhadoalho.enums.Status;
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
	private Status ativo;
	
	private Double peso;

	private Double valorCusto;

	private Double valorVenda;

	private Double margemLucro;
	
	@ManyToOne
	@JoinColumn(name = "idFornecedor", nullable = false)
	private Fornecedor fornecedor;

	/*
	 * @OneToMany(mappedBy = "produto") private Set<ItemPedido> itensPedido = new
	 * HashSet<>();
	 */
}
