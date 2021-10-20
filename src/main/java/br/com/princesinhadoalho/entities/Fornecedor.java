package br.com.princesinhadoalho.entities;

import java.io.Serializable;
import java.util.List;

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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "fornecedor")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1788516841208424794L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idFornecedor")
	private Integer idFornecedor;

	@Column(length = 100, nullable = false)
	private String nome;

	@Getter @Setter
	@ManyToOne
	@JoinColumn(name = "idEndereco", nullable = false)
	private Endereco endereco;

	@Getter @Setter
	@ManyToOne
	@JoinColumn(name = "idContato", nullable = false)
	private Contato contato;
	
	@Getter @Setter
	@OneToMany(mappedBy = "fornecedor") //1 fornecedor para muitos Produtos
	private List<Produto> produtos; //atrib na classe produto onde foi mapeado

}
