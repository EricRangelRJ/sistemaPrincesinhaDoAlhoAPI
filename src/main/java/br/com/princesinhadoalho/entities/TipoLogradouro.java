package br.com.princesinhadoalho.entities;

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
@Table(name = "tipoLogradouro")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TipoLogradouro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoLogradouro;

	@Column(length = 100, nullable = false)
	private Integer tipo;

}
