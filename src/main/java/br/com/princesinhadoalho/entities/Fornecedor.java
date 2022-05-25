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
@Table(name = "fornecedores")
public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1788516841208424794L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idFornecedor")
	private Integer idFornecedor;

	@Column(length = 100, nullable = false)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "idEndereco", nullable = false)
	private Endereco endereco;

	@ManyToOne
	@JoinColumn(name = "idContato", nullable = false)
	private Contato contato;

	@OneToMany(mappedBy = "fornecedor") 
	private Set<Produto> produtos = new HashSet<>(); 

}
*/