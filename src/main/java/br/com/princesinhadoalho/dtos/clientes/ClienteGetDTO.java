package br.com.princesinhadoalho.dtos.clientes;

import br.com.princesinhadoalho.entities.ClienteEntity;
import br.com.princesinhadoalho.helpers.DateHelper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteGetDTO {

	private Integer idCliente;
	private String nome;
	private String cpf;
	private String dataNascimento;
	private String email;
	private String observacao;
	
	
	// Convertendo um cliente em Dto via construtor
	public ClienteGetDTO(ClienteEntity cliente) {
		
		this.idCliente = cliente.getIdCliente();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		
		if(cliente.getDataNascimento() != null) {
			this.dataNascimento = DateHelper.toString(cliente.getDataNascimento());
		}
		this.email = cliente.getEmail();
		this.observacao = cliente.getObservacao();
		}
		
}
