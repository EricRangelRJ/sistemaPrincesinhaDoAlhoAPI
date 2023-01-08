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
public class TipoLogradouroDTO {

	private String descricao;

	// Convertendo um tipo logradouro em Dto via construtor
	public TipoLogradouroDTO(TipoLogradouroEntity tipoLogradouro) {

		this.descricao = tipoLogradouro.getDescricao();
	}
}
