package br.com.princesinhadoalho.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.princesinhadoalho.enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enderecos")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEndereco")
	private Integer idEndereco;

	@Column(length = 60)
	private String logradouro;

	@Column(length = 10)
	private String numero;
	
	@Column(length = 60)
	private String complemento;
	
	@Column(length = 60)
	private String condominio;

	@Column(length = 60)
	private String bairro;
	
	@Column(length = 60)
	private String municipio;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@Column(length = 9)
	private String cep;

	@OneToMany(mappedBy = "endereco")
	private Set<Cliente> clientes = new HashSet<>();

	@OneToOne(mappedBy = "endereco", cascade = CascadeType.ALL)
	private Fornecedor fornecedor;
	
}
