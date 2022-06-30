package br.com.princesinhadoalho.dtos.enderecos;

import br.com.princesinhadoalho.entities.Endereco;
import br.com.princesinhadoalho.enums.Estado;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
		
	public EnderecoDTO() {
	}
	
	public EnderecoDTO(Endereco endereco) {
		this.idEndereco = endereco.getIdEndereco();
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
