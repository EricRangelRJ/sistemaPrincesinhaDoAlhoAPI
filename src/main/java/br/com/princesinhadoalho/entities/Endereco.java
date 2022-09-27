package br.com.princesinhadoalho.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.princesinhadoalho.enums.Estado;
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

	@Column(length = 60)
	private String logradouro;

	@Column(length = 10)
	private String numero;
	
	@Column(length = 60)
	private String complemento;
	
	@Column(length = 60)
	private String condominio;

	@Column(length = 60)
	private String bairro;
	
	@Column(length = 60)
	private String municipio;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@Column(length = 9)
	private String cep;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "endereco")
	private Set<Cliente> clientes = new HashSet<>();

	@OneToOne(mappedBy = "endereco", cascade = CascadeType.ALL)
	private Fornecedor fornecedor;

	public Endereco(Integer idEndereco, String logradouro, String numero, String complemento, String condominio, String bairro,
			String municipio, Estado estado, String cep) {
		this.idEndereco = idEndereco;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.condominio = condominio;
		this.bairro = bairro;
		this.municipio = municipio;
		this.estado = estado;
		this.cep = cep;
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
		return Objects.equals(cep, other.cep) && Objects.equals(complemento, other.complemento)
				&& Objects.equals(idEndereco, other.idEndereco) && Objects.equals(logradouro, other.logradouro)
				&& Objects.equals(numero, other.numero);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cep, complemento, idEndereco, logradouro, numero);
	}
	
}
