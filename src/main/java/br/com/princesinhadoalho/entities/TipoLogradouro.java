package br.com.princesinhadoalho.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tiposLogradouro")
public class TipoLogradouro implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTipoLogradouro")
	private Integer idTipoLogradouro;

	@Column(length = 100, nullable = false)
	private Integer tipo;
	
	/*TODO
	@OneToMany(mappedBy = "tipoLogradouro")
	private List<Endereco> enderecos;*/
	
	public TipoLogradouro() {
		// TODO Auto-generated constructor stub
	}

	public TipoLogradouro(Integer idTipoLogradouro, Integer tipo) {
		super();
		this.idTipoLogradouro = idTipoLogradouro;
		this.tipo = tipo;
	}

	public Integer getIdTipoLogradouro() {
		return idTipoLogradouro;
	}

	public void setIdTipoLogradouro(Integer idTipoLogradouro) {
		this.idTipoLogradouro = idTipoLogradouro;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

}

/*
 * package br.com.princesinhadoalho.entities;
 * 
 * import java.util.List;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
 * import javax.persistence.Id; import javax.persistence.OneToMany; import
 * javax.persistence.Table;
 * 
 * import lombok.AllArgsConstructor; import lombok.EqualsAndHashCode; import
 * lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter; import
 * lombok.ToString;
 * 
 * @Entity
 * 
 * @Table(name = "tipoLogradouro")
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @ToString
 * 
 * @EqualsAndHashCode
 * 
 * @NoArgsConstructor
 * 
 * @AllArgsConstructor public class TipoLogradouro {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY)
 * 
 * @Column(name = "idTipoLogradouro") private Integer idTipoLogradouro;
 * 
 * @Column(length = 100, nullable = false) private Integer tipo;
 * 
 * @OneToMany(mappedBy = "tipoLogradouro") private List<Endereco> enderecos;
 * 
 * }
 */