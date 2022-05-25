package br.com.princesinhadoalho.dtos.clientes;

import br.com.princesinhadoalho.dtos.enderecos.EnderecoGetDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteGetDTO {

	private Integer idCliente;
	private String nome;
	private String dataNascimento;
	private String cpf;
	private EnderecoGetDTO endereco;

}
