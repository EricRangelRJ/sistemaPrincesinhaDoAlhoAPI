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

	@Column(length = 100, nullable = false)
	private String nome;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataNascimento")
	private Date dataNascimento;

	@Column(length = 14, unique = true)
	private String cpf;
	
	@ManyToOne
	@JoinColumn(name = "idEndereco", nullable = false)
	private Endereco endereco;

/*	@ManyToOne
	@JoinColumn(name = "idContato")
	private Contato contato;

	@OneToMany(mappedBy = "cliente")
	private Set<Pedido> pedidos = new HashSet<>();
 */
}
