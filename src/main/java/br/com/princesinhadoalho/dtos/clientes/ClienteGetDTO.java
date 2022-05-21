package br.com.princesinhadoalho.dtos.clientes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteGetDTO {

	private Integer idCliente;
	private String nome;
	private String dataNascimento;
	private String cpf;
	private Integer contato;
	private Integer endereco;
}
