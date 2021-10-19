package br.com.princesinhadoalho.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@Getter
	@Setter
	private TipoLogradouro tipoLogradouro;

}
