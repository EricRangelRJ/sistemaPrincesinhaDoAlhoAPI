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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "condominio")
public class Condominio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCondominio")
	private Integer idCondominio;

	@Column(length = 100, nullable = false)
	private String nomeCondominio;

	@ManyToOne // Muitos Condomínios para um Endereço
	@JoinColumn(name = "idEndereco", nullable = false)
	private Endereco endereco;

}