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
public class EnderecoPostDTO {

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
	public EnderecoPostDTO(EnderecoEntity endereco) {
		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
		this.complemento = endereco.getComplemento();
		this.condominio = endereco.getCondominio();
		this.bairro = endereco.getBairro();
		this.municipio = endereco.getMunicipio();
		this.estado = endereco.getEstado();
		this.cep = endereco.getCep();
	}

}
