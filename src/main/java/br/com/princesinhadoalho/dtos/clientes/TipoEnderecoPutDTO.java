package br.com.princesinhadoalho.dtos.clientes;

import br.com.princesinhadoalho.entities.TipoEnderecoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoEnderecoPutDTO {

	private Integer idTipoEndereco;
	private String descricao;

	// Convertendo um tipo Endereco em Dto via construtor
	public TipoEnderecoPutDTO(TipoEnderecoEntity tipoEndereco) {

		this.idTipoEndereco = tipoEndereco.getIdTipoEndereco();
		this.descricao = tipoEndereco.getDescricao();
	}
}
