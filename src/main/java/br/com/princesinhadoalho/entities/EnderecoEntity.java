package br.com.princesinhadoalho.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.princesinhadoalho.enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "endereco")
public class EnderecoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_endereco")
	private Integer idEndereco;

	@Column(length = 60)
	private String logradouro;

	@Column(length = 10)
	private String numero;
	
	@Column(length = 100)
	private String complemento;
	
	@Column(length = 60)
	private String condominio;

	@Column(length = 60)
	private String bairro;
	
	@Column(length = 60)
	private String municipio;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@Column(length = 15)
	private String cep;
	
	@Column(name = "ic_de_entrega")
	private Character isEnderecoDeEntrega;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_cliente")
	private ClienteEntity cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_tipo_endereco")
	private TipoEnderecoEntity tipoEndereco;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_tipo_logradouro")
	private TipoLogradouroEntity tipoLogradouro;
	
	public EnderecoEntity(String logradouro, String numero, String complemento, String condominio, String bairro,
			String municipio, Estado estado, String cep) {
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
	public int hashCode() {
		return Objects.hash(cep, complemento, idEndereco, logradouro, numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnderecoEntity other = (EnderecoEntity) obj;
		return Objects.equals(cep, other.cep) && Objects.equals(complemento, other.complemento)
				&& Objects.equals(idEndereco, other.idEndereco) && Objects.equals(logradouro, other.logradouro)
				&& Objects.equals(numero, other.numero);
	}

}
