package br.com.princesinhadoalho.entities;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "cliente")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements Serializable {

	private static final long serialVersionUID = 5853510303381995346L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCliente")
	private Integer idCliente;

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(nullable = false)
	private Date dataNascimento;

	@Column(length = 20, unique = true)
	private String cpf;
	
	@ManyToOne //Muitos (Clientes) para um (Endereço)
	@JoinColumn(name = "idEndereco", nullable = false) //FK
	private Endereco enderecos;

}
