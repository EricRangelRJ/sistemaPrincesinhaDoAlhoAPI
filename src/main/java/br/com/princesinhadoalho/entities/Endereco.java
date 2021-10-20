package br.com.princesinhadoalho.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "endereco")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Endereco implements Serializable {

	private static final long serialVersionUID = -3348795277148369677L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEndereco")
	private Integer idEndereco;

	@Column(length = 100, nullable = false)
	private String logradouro;

	@Column(length = 15)
	private String cep;

	@Column(length = 10, nullable = false)
	private String numero;

	@Column(length = 100)
	private String complemento;

	@Column(length = 500)
	private String observacao;

	@Column(length = 1)
	private boolean condominio;
	
	@OneToOne
	@Getter @Setter
	@JoinColumn(name = "idTipoLogradouro", nullable = false)
	private TipoLogradouro tipoLogradouro;
	
	@OneToMany(mappedBy = "endereco")
	private List<Cliente> clientes;
	
	@OneToMany(mappedBy = "endereco")
	private List<Condominio> condominios;

	@OneToMany(mappedBy = "endereco")
	private List<Fornecedor> fornecedores;

}
