package br.com.princesinhadoalho.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enderecos")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@OneToMany(mappedBy = "endereco")
	private Set<Cliente> clientes = new HashSet<>();

	@OneToOne(mappedBy = "endereco", cascade = CascadeType.ALL)
	private Condominio condominio;
	
/*	@ManyToOne
	@JoinColumn(name = "idTipoLogradouro", nullable = false)
	private TipoLogradouro tipoLogradouro;

	@OneToMany(mappedBy = "endereco")
	private Set<Fornecedor> fornecedores = new HashSet<>();
*/

}
