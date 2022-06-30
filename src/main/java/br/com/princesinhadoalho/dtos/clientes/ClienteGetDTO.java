package br.com.princesinhadoalho.dtos.clientes;

import br.com.princesinhadoalho.dtos.enderecos.EnderecoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteGetDTO {

	private Integer idCliente;
	private String nome;
	private String cpf;
	private String dataNascimento;
	private String telefone1;
	private String telefone2;
	private String email;
	private String observacao;
	private EnderecoDTO endereco;
	
	

}
