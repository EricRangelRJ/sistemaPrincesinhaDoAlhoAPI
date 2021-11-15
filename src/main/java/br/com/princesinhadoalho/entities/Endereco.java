package br.com.princesinhadoalho.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
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

	@Column(length = 1)
	private boolean condominio;
	
	/*TODO
	@ManyToOne
	@JoinColumn(name = "idTipoLogradouro", nullable = false)
	private TipoLogradouro tipoLogradouro;
	
	@OneToMany(mappedBy = "endereco")
	private List<Cliente> clientes;
	
	@OneToMany(mappedBy = "endereco")
	private List<Condominio> condominios;

	@OneToMany(mappedBy = "endereco")
	private List<Fornecedor> fornecedores; */
	
	public Endereco() {
		// TODO Auto-generated constructor stub
	}

	public Endereco(Integer idEndereco, String logradouro, String cep, String numero, String complemento,
			String observacao, boolean condominio) {
		super();
		this.idEndereco = idEndereco;
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
		this.observacao = observacao;
		this.condominio = condominio;
		
	}

	public Integer getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public boolean isCondominio() {
		return condominio;
	}

	public void setCondominio(boolean condominio) {
		this.condominio = condominio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEndereco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(idEndereco, other.idEndereco);
	}

}



