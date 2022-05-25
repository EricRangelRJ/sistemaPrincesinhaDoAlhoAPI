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
@Table(name = "vendedor")
public class Vendedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVendedor")
	private Integer idVendedor;

	@Column(length = 100, nullable = false)
	private String nomeCompleto;

	@Column(length = 50, nullable = false)
	private String apelido;
	
	@OneToMany(mappedBy = "vendedor")
	private Set<Pedido> pedidos = new HashSet<>();

}
**/