/*package br.com.princesinhadoalho.entities;

import java.io.Serializable;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contatos")
public class Contato implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idContato")
	private Integer idContato;
	
	@Column(length = 100, nullable = false)
	private String contato;
	
	@ManyToOne
	@JoinColumn(name = "idTipoContato", nullable = false)
	private TipoContato tipoContato; 

	@OneToMany(mappedBy = "contato")
	private Set<Cliente> clientes = new HashSet<>();

	
	@OneToMany(mappedBy = "contato")
	private Set<Fornecedor> fornecedores = new HashSet<>();
	
}
*/