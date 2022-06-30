package br.com.princesinhadoalho.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCliente")
	private Integer idCliente;

	@Column(length = 60, nullable = false)
	private String nome;
	
	@Column(length = 14, nullable = false)
	private String cpf;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	
	@Column(length = 15, nullable = false)
	private String telefone1;
	
	@Column(length = 15)
	private String telefone2;
	
	@Column(length = 60)
	private String email;

	@Column(columnDefinition = "TEXT")
	private String observacao;

	@ManyToOne
	@JoinColumn(name = "idEndereco")
	private Endereco endereco;
	
	@OneToMany(mappedBy = "cliente")
	private Set<Pedido> pedidos = new HashSet<>();

}
