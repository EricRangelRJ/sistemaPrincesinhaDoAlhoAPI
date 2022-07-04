package br.com.princesinhadoalho.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	private String nomeFornecedor;
	
	@Column(length = 20, nullable = false, unique = true)
	private String cpfCnpj;
	
	@Column(length = 15, nullable = false, unique = true)
	private String telefone1;
	
	@Column(length = 15, unique = true)
	private String telefone2;
	
	@Column(length = 60, unique = true)
	private String email;

	@OneToOne
	@JoinColumn(name = "idEndereco", unique = true)
	private Endereco endereco;
	
	@OneToMany(mappedBy = "fornecedor") 
	private Set<Produto> produtos = new HashSet<>();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		return Objects.equals(cpfCnpj, other.cpfCnpj) || Objects.equals(email, other.email)
				|| Objects.equals(telefone1, other.telefone1) || Objects.equals(telefone2, other.telefone2);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpfCnpj, email, telefone1, telefone2);
	}
	
	
}
