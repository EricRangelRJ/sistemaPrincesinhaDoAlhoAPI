package br.com.princesinhadoalho.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "condominio")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Condominio implements Serializable {

	private static final long serialVersionUID = -433258258985834497L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCondominio")
	private Integer idCondominio;

	@Column(length = 100, nullable = false)
	private String nomeCondominio;

	@Getter
	@Setter
	@ManyToOne // Muitos (Clientes) para um (Endereço)
	@JoinColumn(name = "idEndereco", nullable = false) // FK
	private Endereco endereco;

}
