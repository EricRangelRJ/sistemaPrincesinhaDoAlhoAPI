/*package br.com.princesinhadoalho.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tiposLogradouro")
public class TipoLogradouro implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTipoLogradouro")
	private Integer idTipoLogradouro;

	@Column(length = 100, nullable = false)
	private Integer tipo;
	
	@OneToMany(mappedBy = "tipoLogradouro")
	private Set<Endereco> enderecos = new HashSet<>();
}
*/