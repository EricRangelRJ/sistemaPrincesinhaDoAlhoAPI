package br.com.princesinhadoalho.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tiposContato")
public class TipoContato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTipoContato")
	private Integer idTipoContato;
	
	@Column(length = 100, nullable = false)
	private String tipoContato;

	public TipoContato() {
		// TODO Auto-generated constructor stub
	}

	public TipoContato(Integer idTipoContato, String tipoContato) {
		super();
		this.idTipoContato = idTipoContato;
		this.tipoContato = tipoContato;
	}

	public Integer getIdTipoContato() {
		return idTipoContato;
	}

	public void setIdTipoContato(Integer idTipoContato) {
		this.idTipoContato = idTipoContato;
	}

	public String getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(String tipoContato) {
		this.tipoContato = tipoContato;
	}
	
}

/*package br.com.princesinhadoalho.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tipoContato")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TipoContato implements Serializable {
	
	private static final long serialVersionUID = 7144319001359379170L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTipoContato")
	private Integer idTipoContato;
	
	@Column(length = 100, nullable = false)
	private String tipoContato;

}
*/