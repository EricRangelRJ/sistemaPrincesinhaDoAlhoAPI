package br.com.princesinhadoalho.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tipo_logradouro")
public class TipoLogradouroEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idtipo_logradouro")
	private Integer idTipoLogadouro;
	
	@Column
	private String descricao;
	
	@OneToMany(mappedBy = "tipoLogradouro")
	private List<EnderecoEntity> enderecos;

}
