package br.com.princesinhadoalho.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contatos")
public class Contato implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idContato")
	private Integer idContato;
	
	@Column(length = 100, nullable = false)
	private String contato;
	
	/*TODO
	@OneToMany(mappedBy = "contato")
	private List<Cliente> clientes;
	
	@ManyToOne
	@JoinColumn(name = "idTipoContato", nullable = false)
	private TipoContato tipocontato; */

	public Contato() {
		// TODO Auto-generated constructor stub
	}

	public Contato(Integer idContato, String contato) {
		super();
		this.idContato = idContato;
		this.contato = contato;
		
	}

	public Integer getIdContato() {
		return idContato;
	}

	public void setIdContato(Integer idContato) {
		this.idContato = idContato;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}
	
	

}
