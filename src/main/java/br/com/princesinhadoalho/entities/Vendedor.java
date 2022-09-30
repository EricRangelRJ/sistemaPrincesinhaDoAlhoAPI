package br.com.princesinhadoalho.entities;

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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "vendedor")
public class Vendedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVendedor")
	private Integer idVendedor;

	@Getter
	@Setter
	@Column(length = 100, nullable = false)
	private String nome;

	@Getter
	@Setter
	@Column(length = 50)
	private String apelido;
	
	@Getter
	@Setter
	@OneToMany(mappedBy = "vendedor")
	private Set<Pedido> pedidos = new HashSet<>();

}
