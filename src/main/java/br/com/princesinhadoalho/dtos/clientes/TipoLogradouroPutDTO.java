package br.com.princesinhadoalho.dtos.clientes;

import br.com.princesinhadoalho.entities.TipoLogradouroEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoLogradouroPutDTO {

	private Integer idTipoLogradouro;
	private String descricao;

	// Convertendo um tipo logradouro em Dto via construtor
	public TipoLogradouroPutDTO(TipoLogradouroEntity tipoLogradouro) {

		this.idTipoLogradouro = tipoLogradouro.getIdTipoLogadouro();
		this.descricao = tipoLogradouro.getDescricao();
	}
}
