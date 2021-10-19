package br.com.princesinhadoalho.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "produto")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Produto implements Serializable {

	private static final long serialVersionUID = 8023828852871481556L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Getter
	@Setter
	private Fornecedor fornecedor;

}
