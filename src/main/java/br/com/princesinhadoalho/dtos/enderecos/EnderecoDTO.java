package br.com.princesinhadoalho.dtos.enderecos;

import br.com.princesinhadoalho.dtos.clientes.TipoEnderecoDTO;
import br.com.princesinhadoalho.dtos.clientes.TipoLogradouroDTO;
import br.com.princesinhadoalho.entities.EnderecoEntity;
import br.com.princesinhadoalho.enums.Estado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoDTO {

	private Integer idEndereco;
	private String logradouro;
	private String numero;
	private String complemento;
	private String condominio;
	private String bairro;
	private String municipio;
	private Estado estado;
	private String cep;
	private Character isEnderecoDeEntrega;
	private TipoLogradouroDTO tipoLogradouro;
	private TipoEnderecoDTO tipoEndereco;
	
		
	// convertendo um endereço em dto via construtor
	public EnderecoDTO(EnderecoEntity enderecoEntity) {
		this.idEndereco = enderecoEntity.getIdEndereco();
		this.logradouro = enderecoEntity.getLogradouro();
		this.numero = enderecoEntity.getNumero();
		this.complemento = enderecoEntity.getComplemento();
		this.condominio = enderecoEntity.getCondominio();
		this.bairro = enderecoEntity.getBairro();
		this.municipio = enderecoEntity.getMunicipio();
		this.estado = enderecoEntity.getEstado();
		this.cep = enderecoEntity.getCep();
	}

}
